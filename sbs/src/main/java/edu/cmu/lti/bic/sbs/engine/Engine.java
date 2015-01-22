package edu.cmu.lti.bic.sbs.engine;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.ui.UserInterface;
import edu.cmu.lti.bic.sbs.ui.UserInterfaceInitializationException;

public class Engine {
	public Engine() {
		UserInterface ui = null;
		try {
			// User interface initialization
			System.out.println("Initializing the user interface");
			ui = new UserInterface(this);
		} catch (UserInterfaceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
