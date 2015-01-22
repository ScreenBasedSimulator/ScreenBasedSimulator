package edu.cmu.lti.bic.sbs.engine;

<<<<<<< HEAD
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
 * @author Xiaoxu Lu <xiaoxulu@andrew.cmu.edu>
 *
 */
public class Engine {

	/*
	 * Constructor function, responsible for creating UserInterface,
	 * Simulator and Evaluator
	 */
	public Engine() {
		// User interface initialization
=======
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.ui.UserInterface;
import edu.cmu.lti.bic.sbs.ui.UserInterfaceInitializationException;

public class Engine {
	public Engine() {
>>>>>>> 101ebd7ca963ed0898fa31b12c8d2f9b628daea7
		UserInterface ui = null;
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
		Simulator sim = new Simulator(pt);
		
		// Evaluator initialization
		//Evaluator eval = new Evaluator();
	}
	
	public void callCode(String code) {
		
	}
	
	public void connectMonitor() {
		
	}
	
	public void useTool(Tool tool){
		
	}
	
	public void useDrug(Drug drug, Double dose) {
		
	}
}
