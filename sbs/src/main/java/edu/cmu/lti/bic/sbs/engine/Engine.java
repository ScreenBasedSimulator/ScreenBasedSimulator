package edu.cmu.lti.bic.sbs.engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Timer;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.simulator.Simulator;
import edu.cmu.lti.bic.sbs.ui.UserInterface;

/**
 * The Engine Class
 * 
 * @author Xiaoxu Lu
 *
 */
public class Engine {
	UserInterface ui = null;
	Simulator sim = null;
	Evaluator eval = null;
	Scenario scen = null;
	Calendar time = Calendar.getInstance();
	Timer timer = new Timer();
	private Gson gson = new Gson();

	boolean isMonitorConnected = false;

	/*
	 * Constructor function, responsible for creating UserInterface, Simulator and
	 * Evaluator
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

		// Scenario initialization
		scen = new Scenario(ui);
		// Load Tool data to user interface
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("src/test/resources/equipmentTest.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Tool[] tools = gson.fromJson(fileReader, Tool[].class);
		// tools to ui
		for (Tool tool : tools) {
			ui.addTool(tool);
		}
		// Load Patient data to user interface
		try {
			fileReader = new FileReader("src/test/resources/patientTest.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// patient to ui and simulation
		Patient patient = gson.fromJson(fileReader, Patient.class);
		ui.setPatientInfo(patient);

		// Patient and Simulator initialization
		// Raw data should be loaded by file input later...

		sim = new Simulator(patient);

		// Evaluator initialization
		eval = new Evaluator();
		// Start looping

		timer.scheduleAtFixedRate(new CoreTimerTask(1000, this), 0, 1000);
	}

	/*
	 * process() start a scenario simulation
	 */

	public void process() {

		String code = "code blue";
		scen.callCode(code);

		scen.connectMonitor();

		// scen.useDrug(drug, dose);

		// Tool tool = new Tool();
		// scen.useTool(tool);
	}

	public void useTool(Tool tool) {
		scen.useTool(tool);
		eval.receive(tool, time);
		sim.simulateWithTool(tool);

	}

	public void useDrug(Prescription p) {
		scen.useDrug(p.getDrug(), p.getDose());
		eval.receive(p, time);
		sim.simWithDrugs(p.getDrug(), p.getDose());
	}

	public void update(int interval) {
		time.add(Calendar.MILLISECOND, interval);
		ui.updateTime(time);

		Patient p = sim.simPatient();
		eval.regularUpdate(p, time);
		if (isMonitorConnected) {
			ui.updateMonitor(p);
		}
	}

	public void connectMonitor() {
		isMonitorConnected = true;
	}
}
