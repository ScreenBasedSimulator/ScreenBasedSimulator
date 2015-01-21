package edu.cmu.lti.bic.sbs.engine;

import edu.cmu.lti.bic.sbs.ui.UserInterface;
import edu.cmu.lti.bic.sbs.ui.UserInterfaceInitializationException;

public class engineControler {
	public engineControler() {
		UserInterface ui = null;
		try {
			// User interface initialization
			System.out.println("Initializing the user interface");
			ui = new UserInterface(null);
		} catch (UserInterfaceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
