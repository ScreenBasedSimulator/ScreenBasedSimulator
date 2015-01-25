package edu.cmu.lti.bic.sbs.engine;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.simulator.BloodPressure;
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
		
		BloodPressure bp = new BloodPressure(100.0f);
		HeartRate hr = new HeartRate(80.0f);
		OxygenLevel ol = new OxygenLevel(50.0f);
		//RespirationRate
		RepositoryRate rr = new RepositoryRate(60.0f);
		
		Patient pt = new Patient(bp, hr, ol, rr);
		sim = new Simulator(pt);
		
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
