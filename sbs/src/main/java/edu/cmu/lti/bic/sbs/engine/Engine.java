package edu.cmu.lti.bic.sbs.engine;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.OxygenMask;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.BloodPressure;
import edu.cmu.lti.bic.sbs.simulator.Condition;
import edu.cmu.lti.bic.sbs.simulator.HeartRate;
import edu.cmu.lti.bic.sbs.simulator.OxygenLevel;
import edu.cmu.lti.bic.sbs.simulator.Patient;
import edu.cmu.lti.bic.sbs.simulator.RepositoryRate;
import edu.cmu.lti.bic.sbs.simulator.Simulator;
import edu.cmu.lti.bic.sbs.ui.UserInterface;
import edu.cmu.lti.bic.sbs.ui.UserInterfaceInitializationException;

/**
 * The Engine Class
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
	
	Tool eqOM = null;
	Drug drug = null;
	float dose = 0;
	/*
	 * Constructor function, responsible for creating UserInterface,
	 * Simulator and Evaluator
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
		
		// Patient and Simulator initialization 
		// Raw data should be loaded by file input later...
		
		//four default medical parameter
		BloodPressure bp = new BloodPressure(100.0f);
		HeartRate hr = new HeartRate(80.0f);
		OxygenLevel ol = new OxygenLevel(50.0f);
		RepositoryRate rr = new RepositoryRate(60.0f);
		
		Condition defaultCondition = new Condition(bp, hr, ol, rr);
		
		//initialize a patient and a simulator
		Patient pt = new Patient(defaultCondition);
		
		//new a simulator to control patient
		sim = new Simulator(pt);
		
		//image a tool
		String id = "001";
		String name = "tool1";
		String description = "the tool can increse the oxygen level";
		
		eqOM = new OxygenMask(id, name, description, 20);
		//initialize a tool, a drug and its dose
		drug = new Drug();
		dose = (float) (3.1);
		
		
		//new a runnable
		Runnable newRunnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true){
					
					//each second involve the function to update patient's stage        
					sim.updatePatient(eqOM, drug, dose);
					
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
		
		
		// Evaluator initialization
		eval = new Evaluator();
	}
	/*
	 * process() start a scenario simulation
	 */
	public void process(){
		
		String code = "code blue";
		scen.callCode(code);
		
		scen.connectMonitor();
		
		//scen.useDrug(drug, dose);
		
		//Tool tool = new Tool();
		//scen.useTool(tool);
	}
	
}
