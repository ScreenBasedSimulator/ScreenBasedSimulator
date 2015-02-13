package edu.cmu.lti.bic.sbs.engine;


import java.util.ArrayList;
import java.util.List;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.OxygenMask;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.BloodPressure;
import edu.cmu.lti.bic.sbs.simulator.Condition;
import edu.cmu.lti.bic.sbs.simulator.HeartRate;
import edu.cmu.lti.bic.sbs.simulator.OxygenLevel;
import edu.cmu.lti.bic.sbs.simulator.RespirationRate;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.simulator.Simulator;
import edu.cmu.lti.bic.sbs.ui.UserInterface;
import edu.cmu.lti.bic.sbs.ui.UserInterfaceInitializationException;

/**
 * The Engine Class
 * 
 * @author Xiaoxu Lu <xiaoxul@andrew.cmu.edu>
 *
 */
public class Engine {
	UserInterface ui = null;
	Simulator sim = null;
	Evaluator eval = null;
	Scenario scen = null;
	
	//
	Patient pt = null;
	
	
	List<Tool> toolList = new ArrayList<Tool>();
	List<Drug> drugList = new ArrayList<Drug>();

	private Gson gson = new Gson();

	/*
	 * Constructor function, responsible for creating UserInterface, Simulator
	 * and Evaluator
	 */
	public Engine() {
		// Scenario initialization
		scen = new Scenario();

		// User interface initialization
		try {
			System.out.println("Initializing the user interface");
			ui = new UserInterface(this);
		} catch (UserInterfaceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Load Tool data to user interface
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("src/test/resources/equipmentTest.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Tool[] tools = gson.fromJson(fileReader, Tool[].class);
		for (Tool tool : tools) {
			ui.addTool(tool);
		}
		try {
			fileReader = new FileReader("src/test/resources/patientTest.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Patient patient = gson.fromJson(fileReader, Patient.class);
		ui.setPatientInfo(patient);

		// Patient and Simulator initialization
		// Raw data should be loaded by file input later...

		
		//four default medical parameter
		BloodPressure bp = new BloodPressure(100.0f);
		HeartRate hr = new HeartRate(80.0f);
		OxygenLevel ol = new OxygenLevel(50.0f);
		RespirationRate rr = new RespirationRate(60.0);
		
		Condition defaultCondition = new Condition(bp, hr, ol, rr);
		
		//initialize a patient and a simulator
		Patient pt = new Patient(defaultCondition);
		
		//new a simulator to control patient
		sim = new Simulator(pt);
		
		//image a tool
		String idTool = "001";
		String nameTool = "oxygenMask";
		String descriptionTool = "the tool can increse the oxygen level";
		float oxygenLevel = 80;
		
		Tool eqOM = new OxygenMask(idTool, nameTool, descriptionTool, oxygenLevel);
		//initialize a tool, a drug and its dose
		
		//add to the tool list
		toolList.add(eqOM);
		
		String idDrug = "101";
		String nameDrug = "drug1";
		String descriptionDrug = "the drug can increase the blood pressure";
		float dose = (float) 3.2;
		
		Drug drug = new Drug(idDrug, idDrug, idDrug, dose);
		
		//add to the drug list
		drugList.add(drug);
		
		//new a runnable
		Runnable newRunnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true){
					
					//each second involve the function to update patient's stage        
					sim.updatePatient(toolList,drugList);
					
					//sleep the thread, each second involve the function
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		//new a thread
		Thread newThread = new Thread(newRunnable);
		
		//start the newThread
		newThread.start();

		
//		System.out.println(sim.simPatient().getBp());
//		System.out.println(sim.simPatient().getHr());
//		System.out.println(sim.simPatient().getOl());
//		System.out.println(sim.simPatient().getRr());
		
		
		sim = new Simulator(patient);
		// Evaluator initialization
		eval = new Evaluator();
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
	}

}
