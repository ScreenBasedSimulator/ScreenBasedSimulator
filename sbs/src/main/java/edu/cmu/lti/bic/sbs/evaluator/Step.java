package edu.cmu.lti.bic.sbs.evaluator;

import edu.cmu.lti.bic.sbs.gson.*;


/**
 * The Step Class
 * @author Victor Zhao <xinyunzh@andrew.cmu.edu>
 *
 */
public class Step {
	//private Medicine medUsed;
	
	private Timer timeUsed;
	private Tool toolUsed;
	private Prescription prescriptionUsed;
	private Patient patient;
	
	// private undefined patientStatus;
	
	public String getStep() {
		return prescriptionUsed.toString() + timeUsed.toString() + toolUsed.toString();
	}
	
	public Step() {
		prescriptionUsed = new Prescription();
		timeUsed = new Timer();
		toolUsed = new Tool("","","");
		patient = new Patient();
	}
	
	public Step(Patient apatient, Prescription prescription, Tool tool, Timer time){
	  prescriptionUsed = prescription;
	  timeUsed = time;
	  toolUsed = tool;
	  patient = apatient;
	}
	
	public double stepScore(Step a){
	  if(this.toolUsed == a.toolUsed && this.prescriptionUsed == a.prescriptionUsed){
	    return 1.0;
	  }else {
	    return 0.0;
	  }
	}
	
	public static void main(String[] args) {
		Step s = new Step();
		System.out.println(s.getStep());
	}
}
