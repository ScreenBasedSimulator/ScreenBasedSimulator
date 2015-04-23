package edu.cmu.lti.bic.sbs.engine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Calendar;
import java.util.HashMap;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.Setting;
import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.Simulator;
import edu.cmu.lti.bic.sbs.ui.UserInterface;

/**
 * The Scenario Class
 * 
 * @author Xiaoxu Lu
 *
 */
public class Scenario {
	int ScenId;
	String ScenName;
	
	UserInterface ui;
	Patient patient;
	boolean isMonitorConnected;
	
	private FileReader fileReader = null;
	private Gson gson = new Gson();

	HashMap<String, Drug> drugMap = new HashMap<String, Drug>();
	HashMap<String, Tool> toolMap = new HashMap<String, Tool>();

	public Scenario(UserInterface ui) {
		this.ui = ui;
		this.ScenId = 1; // just for test
		System.out.println("I am a new Scenario~~~");

	}

	public Scenario(int id, String name) {
		this.ScenId = id;
		this.ScenName = name;
		isMonitorConnected = false;

		// just for test
		System.out.println("I am a new Scenario~~~");
		System.out.println("My id is " + ScenId);
		System.out.println("My neam is " + ScenName);
	}

	public int getId() {
		return ScenId;
	}

	public String getName() {
		return ScenName;
	}

	public void setName(String name) {
		this.ScenName = name;
	}

	public String toString() {
		return "The Scenario is " + ScenName;
	}

	/*
	 * Interaction functions with all other packages, used by engine class
	 */

	public Drug[] readDrug() {
		try {
			fileReader = new FileReader("src/test/resources/drugTest.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Drug[] drugList = gson.fromJson(fileReader, Drug[].class);
		for (Drug drug : drugList) {
			drugMap.put(drug.getId(), drug);
		}
		return drugList;
	}

	public Patient readPatient() {
		try {
			fileReader = new FileReader("src/test/resources/patientTest.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Patient patient = gson.fromJson(fileReader, Patient.class);
		return patient;
	}

	public Tool[] readTool() {
		try {
			fileReader = new FileReader("src/test/resources/toolTest.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Tool[] toolList = gson.fromJson(fileReader, Tool[].class);
		for (Tool tool : toolList) {
			toolMap.put(tool.getId(), tool);
		}
		return toolList;
	}

	public HashMap<String, Drug> getDrugMap() {
		return drugMap;
	}

	public HashMap<String, Tool> getToolMap() {
		return toolMap;
	}

	public void connectMonitor() {
		isMonitorConnected = true;
	}

	public void useTool(Tool tool, Evaluator evaluator, Simulator simulator,
			Calendar time) {
		System.out.println("Scenario: Tool " + tool.getName() + " is called.");
		evaluator.receive(tool, time);
		evaluator.receive(new Prescription(), time);
		simulator.simulateWithTool(tool);
	}

	public void useDrug(Drug drug, Double dose, Evaluator evaluator,
			Simulator simulator, Calendar time, Prescription p) {
		System.out.println("Scenario: Drug " + dose + " " + drug.getName()
				+ " is used.");
		evaluator.receive(new Tool(), time);
		evaluator.receive(p, time);
		simulator.simWithDrugs(p);
	}

	public void update(UserInterface ui, Evaluator evaluator,
			Simulator simulator, Calendar time) {

		evaluator.receive(time);
		Patient p = simulator.simPatient();
		evaluator.regularUpdate(p, time);
		if (Setting.LOCAL_MODE) {
			if (isMonitorConnected) {
				ui.updateTime(time);
				ui.updateMonitor(p);
			}
		}
	}

	public void restart(Simulator simulator) {
		// patient reset
		patient = readPatient();
		// simulation and reset
		simulator.setPatient(patient);
	}

}
