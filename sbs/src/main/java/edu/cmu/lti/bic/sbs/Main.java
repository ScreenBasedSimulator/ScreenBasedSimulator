package edu.cmu.lti.bic.sbs;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.web.Server;

public class Main {
	public static void main(String[] args) throws Exception {
		
		if (Setting.LOCAL_MODE) {
			new Engine();
		} else {
			Server server = new Server();
			server.start();
		}
		System.out.println("Finished Starting");
	}
}