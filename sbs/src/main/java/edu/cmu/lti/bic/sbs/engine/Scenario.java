package edu.cmu.lti.bic.sbs.engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Tool;

import edu.cmu.lti.bic.sbs.ui.UserInterface;


/**
 * The Scenario Class
 * @author Xiaoxu Lu <xiaoxul@andrew.cmu.edu>
 *
 */
public class Scenario {
	int ScenId;
	String ScenName;
	
	UserInterface ui;
	Patient pt;
	boolean isMonitorConnected;
	
	public Scenario(UserInterface ui){
		//just for test
		this.ui = ui;
		System.out.println("I am a new Scenario~~~");
		
	}
	public Scenario(int id, String name){
		this.ScenId = id;
		this.ScenName = name;
		isMonitorConnected = false;
		
		//just for test
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
	public void callCode(String code) {
		System.out.println("Call code now is " + code + "!!");
	}

	public void connectMonitor() {
		isMonitorConnected = true;
	}

	public void useTool(Tool tool) {
		System.out.println("Scenario: Tool " + tool.getName() + " is called.");
		
	}

	public void useDrug(Drug drug, Double dose) {

	}
}
