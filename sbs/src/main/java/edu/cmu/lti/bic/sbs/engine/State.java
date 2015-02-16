package edu.cmu.lti.bic.sbs.engine;

import java.util.LinkedList;
import java.util.List;

import edu.cmu.lti.bic.sbs.gson.Patient;

public class State {

	private int StateId;
	
	Patient  p; 
	// list used for storing patient for now,
	// using for checkpoint 
	List<Patient> listOfPt;
	int cur;
	
	public State(Patient pt){
		this.StateId = 1;
		this.p = pt;
		listOfPt = new LinkedList<Patient>();
		cur = 0;
		System.out.println("I am a new State with id " + StateId + "!!!");
	}
	
	// store the patient state into listOfPt
	public void setCheckPoint(Patient pt) {
		listOfPt.add(pt);
		cur++;
		System.out.println("Added a new checkpoint indexing at " + cur);
	}
	
	// default return the first state of patient
	public Patient getCheckPoint() {
		if (listOfPt.size() > 0) {
			System.out.println("Return the first state of patient");
			return listOfPt.get(0);
		} else {
			return p;
		}
	}
	
	public int getId(){
		return this.StateId;
	}
	
	public String toString() {
		return "The state is " + StateId;
	}
	
}
