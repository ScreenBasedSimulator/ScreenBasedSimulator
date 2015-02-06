package edu.cmu.lti.bic.sbs.evaluator;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Tool;

/**
 * The Step Class
 * @author Victor Zhao
 * 
 */
public class Step {
	//private Medicine medUsed;
	
	private Timer timeUsed;
	private Drug drugUsed;
	private Tool toolUsed;
	
	
	
	// private undefined patientStatus;
	
	public String getStep() {
		return drugUsed.toString() + timeUsed.toString() + toolUsed.toString();
	}
	
	public Step() {
		drugUsed = new Drug();
		timeUsed = new Timer();
		toolUsed = new Tool("","","");
	}
	
	public static void main(String[] args) {
		Step s = new Step();
		System.out.println(s.getStep());
	}
}
