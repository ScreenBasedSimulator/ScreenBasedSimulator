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
		
	}
	
	public Step(Patient apatient, Prescription prescription, Tool tool, Timer time){
	  prescriptionUsed = prescription;
	  timeUsed = time;
	  toolUsed = tool;
	  patient = apatient;
	}
	
	 public void setPatient(Patient p){
	   patient = p;
	 }
	
	 public Patient getPatient(){
	   return patient;
	 }
	 
	 public void setPrescription(Prescription p){
	   prescriptionUsed = p;
	 }
	 
	 public Prescription getPrescription(){
	   return prescriptionUsed;
	 }
	 
	 public void setTool(Tool t){
	   toolUsed = t;
	 }
	 
	 public Tool getTool(){
	   return toolUsed;
	 }
	 
	 public void setTime(Timer t){
	   timeUsed = t;
	 }
	 
	 public Timer getTime(){
	   return timeUsed;
	 }
	 
	 public boolean isComplete(){
	   return (prescriptionUsed != null) && (patient != null) && (toolUsed != null) && (timeUsed != null);
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
