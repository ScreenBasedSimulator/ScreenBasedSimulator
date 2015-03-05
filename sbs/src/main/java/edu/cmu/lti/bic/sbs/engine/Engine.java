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
	//
	Patient pt = null;
	
	
	List<Tool> toolList = new ArrayList<Tool>();
	List<Drug> drugList = new ArrayList<Drug>();


	Simulator simulator = null;
	Evaluator evaluator = null;
	Scenario scenario = null;
	Calendar time = Calendar.getInstance();
	Timer timer = new Timer();
	private Gson gson = new Gson();

	boolean isMonitorConnected = false;

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

		// Scenario initialization
		scenario = new Scenario(ui);
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
		
		//Load the drug data to user interface
		try {
      fileReader = new FileReader("src/test/resources/drug.json");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
		Drug[] drugMap = gson.fromJson(fileReader, Drug[].class);
		ui.addDrug(drugMap);
		
		// Patient and Simulator initialization
		// Raw data should be loaded by file input later...

		simulator = new Simulator(patient);


		// Evaluator initialization
		evaluator = new Evaluator(this);
		// Start looping

		timer.scheduleAtFixedRate(new CoreTimerTask(1000, this), 0, 1000);
	}

	public void useTool(Tool tool) {
		scenario.useTool(tool);
		//evaluator.receive(tool, time);
		simulator.simulateWithTool(tool);
	}

	public void useDrug(Prescription p) {
		scenario.useDrug(p.getDrug(), p.getDose());
		//evaluator.receive(p, time);
		simulator.simWithDrugs(p);
	}

	public void update(int interval) {
		time.add(Calendar.MILLISECOND, interval);
		ui.updateTime(time);

		Patient p = simulator.simPatient();
		evaluator.regularUpdate(p, time);
		if (isMonitorConnected) {
			ui.updateMonitor(p);
		}
	}

	public void connectMonitor() {
		isMonitorConnected = true;
	}
	
	public void simOver(double score, String report){
		timer.cancel();
		ui.updateReport(score, report);
	}
}
