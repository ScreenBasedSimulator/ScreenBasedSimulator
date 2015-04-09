package edu.cmu.lti.bic.sbs.engine;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Drug;

import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.BloodPressure;
import edu.cmu.lti.bic.sbs.simulator.HeartRate;
import edu.cmu.lti.bic.sbs.simulator.OxygenLevel;
import edu.cmu.lti.bic.sbs.simulator.RespirationRate;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Timer;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Drug;
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
	Patient pt = null;

	List<Tool> toolList = new ArrayList<Tool>();
	List<Drug> drugList = new ArrayList<Drug>();

	Simulator simulator = null;
	Evaluator evaluator = null;
	Scenario scenario = null;
	State state = null;

	Calendar time = Calendar.getInstance();
	Timer timer = new Timer();

	private Gson gson = new Gson();

	boolean isMonitorConnected = false;

	/**
	 * Constructor function, responsible for creating UserInterface, Simulator
	 * and Evaluator
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

		// Scenario initialization
		scenario = new Scenario(ui);

		// Load Tool data to user interface

		// try {
		// fileReader = new FileReader("src/test/resources/toolTest.json");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }

		Tool[] tools = scenario.readTool();
		// tools to ui
		for (Tool tool : tools) {
			ui.addTool(tool);
		}
		// Load Patient data to user interface
		// try {
		// fileReader = new FileReader("src/test/resources/patientTest.json");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }

		Patient patient = scenario.readPatient();
		ui.setPatientInfo(patient);
		state = new State(patient);
		// Load the drug data to user interface
		// try {
		// fileReader = new FileReader("src/test/resources/drugTest.json");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		Drug[] drugMap = scenario.readDrug();
		ui.addDrug(drugMap);

		// Simulator initialization
		simulator = new Simulator(patient);
		// Evaluator initialization
		evaluator = new Evaluator(this);

		// Start looping
		timer.scheduleAtFixedRate(new CoreTimerTask(1000, this), 0, 1000);
	}

	public void connectMonitor() {
		isMonitorConnected = true;
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
		ui.updateTime(time);
		Patient p = simulator.simPatient();
		scenario.update(evaluator,p,state,time);
		if (isMonitorConnected) {
			ui.updateMonitor(p);
		}
	}

	public void recover(int index) {

		pt = state.getCheckpoint(index);
	}

	public void restartSim() {
		// patient reset
		pt = state.getCheckPointZero();
		state.listOfPt.clear();
		simulator.setPatient(pt);
		evaluator = new Evaluator(this);

	}
	public void simOver(double score, String report) {
		timer.cancel();
		ui.updateReport(score, report);
		System.out.println("Finished this scenario simulation~~");
	}
}
