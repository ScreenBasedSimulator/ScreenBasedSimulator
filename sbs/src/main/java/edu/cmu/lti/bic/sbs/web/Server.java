package edu.cmu.lti.bic.sbs.web;

import static spark.Spark.*;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.HashMap;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Report;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class Server {
	private Gson gson = new Gson();
	private Engine engine = null;
	private HashMap<String, Tool> toolMap;
	private HashMap<String, Drug> drugMap;
	private Patient patient;
	private Report report = null;
	private Calendar time = null;
	private boolean isOver = false;
	private HashMap<String, Engine> engineMap;
	
	public Server(){
		engineMap = new HashMap<String, Engine>();
	}
	
	public Server(Engine engine) {
		this.engine = engine;
		this.toolMap = new HashMap<String, Tool>();
		this.drugMap = new HashMap<String, Drug>();
	}
	
	public void setPatientInfo(final Patient patient) {
		this.patient = patient;
	}
	
	public void updateTime(Calendar time) {
		this.time = time;
	}
	
	public void updateReport(Double score, String content) {
		Report report= new Report(score, content);
		this.report = report;
		this.isOver = true;
	}
	
	public void updatePatient(Patient p) {
		assert (p != null);
		assert (p.getBloodPressure() != null);
		assert (p.getHeartRate() != null);
		assert (p.getOxygenLevel() != null);
		assert (p.getRepiratinoRate() != null);
		this.patient = p;
	}
	
	public void addDrug(final Drug drug) {
		assert (drug != null);
		drugMap.put(drug.getId(), drug);
	}
	
	public void addTool(Tool tool) {
		assert (tool != null);
		toolMap.put(tool.getId(), tool);
	}
	// Each user holds a distinguished engine, relationship stored in engineMap
	public Engine getEngine(String name){
		if(engineMap.containsKey(name)){
			return engineMap.get(name);
		}
		else {
			Engine engine = null;
			try {
				engine = new Engine();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			engineMap.put(name, engine);
			return engine;
		}
		
	}
	public void start() {
		port(8080);
		staticFileLocation("/public");
		//still needed?
		post("/:name/new-game", (request, response) -> {
			String name = request.params("name");
			if (engineMap.get(name) == null) {
				engineMap.put(name, new Engine());
			}
			return gson.toJson(new Acknowledgment(200, "OK"));
		});

		get("/:name/regular-update", (req, res) -> {
			Engine engine = getEngine(req.params("name"));
			/* vital, isOver, time */
			if (this.patient == null) {
				return gson.toJson(new Acknowledgment(400, "Resource not found"));
			}
			RegularUpdate regularUpdate = new RegularUpdate(patient.getBloodPressure(),
					patient.getHeartRate(), patient.getOxygenLevel(), patient.getRepiratinoRate(),
					this.time.getTimeInMillis(), this.isOver);
			
			return gson.toJson(regularUpdate);
		});
		get("/:name/patientInfo", (req, res) -> {
			Engine engine = getEngine(req.params("name"));
			return gson.toJson(this.patient);
		});

		get("/:name/report", (req, res) -> {
			Engine engine = getEngine(req.params("name"));
			if (report == null) {
				return gson.toJson(new Acknowledgment(400, "Report is not ready"));
			} else {
				return gson.toJson(this.report);
			}
		});

		post("/:name/tool/:toolname", (req, res) -> {
			Engine engine = getEngine(req.params("name"));
			// code blue
			// use code
				String name = req.params("toolname");

				Tool tool = toolMap.get(name);
				if (tool == null) {
					return gson.toJson(new Acknowledgment(400, "Resource not found"));
				}

				engine.useTool(tool);
				return gson.toJson(new Acknowledgment(200, "Success"));
			});

		post("/:name/drug/:drugname", (req, res) -> {
			Engine engine = getEngine(req.params("name"));
			
			String name = req.params("drugname");

			Drug drug = drugMap.get(name);

			if (drug == null) {
				return gson.toJson(new Acknowledgment(400, "Resource not found"));
			}

			Double dose = null;
			try {
				dose = Double.parseDouble(req.queryParams("dose"));
			} catch (Exception e) {
				return gson.toJson(new Acknowledgment(500, "Parse error"));
			}

			String unit = req.queryParams("unit");

			if (dose == null || unit == null) {
				return gson.toJson(new Acknowledgment(400, "Missing Params"));
			}

			Prescription prescription = new Prescription();
			prescription.setDose(dose).setDrug(drug).setUnit(unit);
			engine.useDrug(prescription);
			return gson.toJson(new Acknowledgment(200, "Success"));
		});
	}
	
}
