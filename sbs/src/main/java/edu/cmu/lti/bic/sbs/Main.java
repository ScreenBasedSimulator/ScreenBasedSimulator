package edu.cmu.lti.bic.sbs;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.web.Server;

public class Main {
	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.start();
		new Engine();
		System.out.println("Finished Starting");
	}
}