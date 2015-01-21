package edu.cmu.lti.bic.sbs.evaluator;

import edu.cmu.lti.bic.sbs.gson.Medicine;

/**
 * The Step Class
 * @author Victor Zhao <xinyunzh@andrew.cmu.edu>
 *
 */
public class Step {
	private Medicine medUsed;
	
	private Timer timeUsed;
	
	private Tool toolUsed;
	
	// private undefined patientStatus;
	
	public String getStep() {
		return medUsed.toString() + timeUsed.toString() + toolUsed.toString();
	}
	
	Step() {
		medUsed = new Medicine();
		timeUsed = new Timer();
		toolUsed = new Tool("","","");
	}
}
