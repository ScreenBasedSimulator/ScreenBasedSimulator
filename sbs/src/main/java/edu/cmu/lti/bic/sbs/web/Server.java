package edu.cmu.lti.bic.sbs.web;

import static spark.Spark.*;

import java.util.HashMap;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.db.DBHelper;
import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Record;
import edu.cmu.lti.bic.sbs.gson.Report;

public class Server {
	private Gson gson = new Gson();
	private HashMap<String, Engine> engineMap;
	
	// Constructor 
	public Server() {
		engineMap = new HashMap<String, Engine>();
	}

	// Each user holds a distinguished engine, relationship stored in engineMap
	public Engine getOrCreateEngine(String name) throws EngineNotFoundException {
		if (engineMap.containsKey(name)) {
			return engineMap.get(name);
		} else {
			throw new EngineNotFoundException();
		}
	}

	public void start() {
		port(8080);
		staticFileLocation("/public");
		
		post("/restart", (req, res) -> {
			String code = req.queryParams("code");
			if (code.equals("codeblue")) {
				engineMap.clear();
				return gson.toJson(new Acknowledgment(200, "OK"));
			}
			return gson.toJson(new Acknowledgment(400, "Wrong Password"));
		});
		
		get("/:name/new-game", (req, res) -> {
			Engine engine = new Engine();
			String name = req.params("name");
			engineMap.put(name, engine);
			engine.setName(name);
			return gson.toJson(new Acknowledgment(200, "OK"));
		});

		get("/:name/regular-update", (req, res) -> {
			Engine engine = getOrCreateEngine(req.params("name"));
			/* vital, isOver, time */
			Patient patient = engine.getPatient();
			if (patient == null) {
				return gson.toJson(new Acknowledgment(400, "Resource not found"));
			}
			RegularUpdate regularUpdate = new RegularUpdate(patient.getBloodPressure(),
					patient.getHeartRate(), patient.getOxygenLevel(), patient.getRepirationRate(),
					engine.getTime().getTimeInMillis(), engine.isStop());
			
			return gson.toJson(regularUpdate);
		});

		get("/:name/patient-info", (req, res) -> {
			Engine engine = getOrCreateEngine(req.params("name"));
			return gson.toJson(engine.getPatient());
		});

		get("/:name/report", (req, res) -> {
			Engine engine = getOrCreateEngine(req.params("name"));
			Report report = engine.getReport();
			if (report == null) {
				return gson.toJson(new Acknowledgment(400,
						"Report is not ready"));
			} else {
				return gson.toJson(report);
			}
		});
		post("/history", (req, res) -> {
			String name = req.queryParams("name");
			Record[] records = DBHelper.displayDataBase(name);
			StringBuilder sb = new StringBuilder();
			for (Record r : records) {
				sb.append(r.toString());
			}
			return sb.toString();
		});

		post("/:name/tool/:toolid",
				(req, res) -> {
					Engine engine = getOrCreateEngine(req.params("name"));

					String id = req.params("toolid");
					try {
						engine.useTool(id);
					} catch (Exception e) {
						return gson.toJson(new Acknowledgment(400,
								"Resource not found"));
					}
					return gson.toJson(new Acknowledgment(200, "Success"));
				});

		post("/:name/drug/:drugid",
				(req, res) -> {
					Engine engine = getOrCreateEngine(req.params("name"));

					String id = req.params("drugid");
					Double dose = null;
					try {
						dose = Double.parseDouble(req.queryParams("dose"));
					} catch (Exception e) {
						return gson.toJson(new Acknowledgment(500,
								"Parse error"));
					}

					String unit = req.queryParams("unit");

					if (dose == null || unit == null) {
						return gson.toJson(new Acknowledgment(400,
								"Missing Params"));
					}
					try {
						engine.useDrug(id, dose, unit);
					} catch (Exception e) {
						return gson.toJson(new Acknowledgment(400,
								"Resource not found"));
					}
					return gson.toJson(new Acknowledgment(200, "Success"));
				});
		
		post("/:name/debrief", (req, res) -> {
			Engine engine = getOrCreateEngine(req.params("name"));
			engine.setDebrief(req.queryParams("debrief"));
			// remove user from engineMap after debriefing
			engineMap.remove(req.params("name"));
			return gson.toJson(new Acknowledgment(200, "Success"));
		});
		
		exception(EngineNotFoundException.class, (e, req, res) -> {
			res.body(gson.toJson(new Acknowledgment(404, "Engine not found")));
		});
	}

}
