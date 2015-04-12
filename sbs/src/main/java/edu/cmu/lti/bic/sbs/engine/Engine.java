package edu.cmu.lti.bic.sbs.engine;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.Simulator;
import edu.cmu.lti.bic.sbs.ui.UserInterface;
import edu.cmu.lti.bic.sbs.web.Server;

/**
 * The Engine Class
 * 
 * @author Xiaoxu Lu
 *
 */
public class Engine {
	UserInterface ui = null;
	// Server server = null;
	Patient pt = null;

	//List<Tool> toolList = new ArrayList<Tool>();
	//List<Drug> drugList = new ArrayList<Drug>();


	Simulator simulator = null;
	Evaluator evaluator = null;
	Scenario scenario = null;
	State state = null;

	Calendar time = Calendar.getInstance();
	Timer timer = new Timer();

	//private Gson gson = new Gson();


	/**
	 * Constructor function, responsible for creating UserInterface, Simulator and
	 * Evaluator
	 * 
	 * @throws Exception
	 */


	public Engine() throws Exception {
		// User interface initialization
		try {
			System.out.println("Initializing the user interface");
			ui = new UserInterface(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// server = new Server(this);
		// server.start();

		// Scenario initialization
		scenario = new Scenario(ui);

		// load tool, drug and patient through scenario
		Tool[] tools = scenario.readTool();
		for (Tool tool : tools) {
			ui.addTool(tool);
			// server.addTool(tool);
		}
		Drug[] drugMap = scenario.readDrug();
		ui.addDrug(drugMap);
		// for (Drug drug:drugMap) {
		// server.addDrug(drug);
		// }
		Patient patient = scenario.readPatient();
		ui.setPatientInfo(patient);
		// server.setPatientInfo(patient);

		state = new State(patient);

		simulator = new Simulator(patient);


		// Evaluator initialization
		evaluator = new Evaluator(this);
		// Start looping

		timer.scheduleAtFixedRate(new CoreTimerTask(1000, this), 0, 1000);
	}

	public void connectMonitor() {
		scenario.connectMonitor();
	}

	public void useTool(Tool tool) {
		scenario.useTool(tool, evaluator, simulator, time);
	}

	public void useDrug(Prescription p) {
		scenario.useDrug(p.getDrug(), p.getDose(), evaluator, simulator, time,
				p);
	}

	public void update(int interval) {
		time.add(Calendar.MILLISECOND, interval);

		// server.updateTime(time);
		scenario.update(ui, evaluator, simulator, state, time);

	}

	public void recover(int index) {
		pt = state.getCheckpoint(index);
	}

	public Patient getPatient() {
		return simulator.getPatient();
	}

	public void restartSim() {
		scenario.restart(simulator, state);
		evaluator = new Evaluator(this);
	}

	public void simOver(double score, String report) {
		timer.cancel();
		ui.updateReport(score, report);
		// server.updateReport(score, report);
	}
}
