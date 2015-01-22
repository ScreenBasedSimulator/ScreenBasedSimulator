package edu.cmu.lti.bic.sbs.engine;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class Scenario {
	int ScenId;
	String ScenName;
	
	public Scenario(){
		//just for test
		System.out.println("I am a new Scenario~~~");
	}
	public Scenario(int id, String name){
		ScenId = id;
		ScenName = name;
		//just for test
		System.out.println("I am a new Scenario~~~");
		System.out.println("My id is " + ScenId);
		System.out.println("My neam is " + ScenName);
	}
	
	
	/*
	 * Interaction functions with all other packages
	 */
	public void callCode(String code) {

	}

	public void connectMonitor() {

	}

	public void useTool(Tool tool) {

	}

	public void useDrug(Drug drug, Double dose) {

	}
}
