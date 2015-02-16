package edu.cmu.lti.bic.sbs.engine;

import java.util.LinkedList;
import java.util.List;

import edu.cmu.lti.bic.sbs.gson.Patient;

public class State {
	int StateId;
	
	Patient  p; 
	// list used for storing patient for now,
	// using for checkpoint 
	List<Patient> listOfPt;
	
	public State(Patient pt){
		this.p = pt;
		listOfPt = new LinkedList<Patient>();
		
	}
	
}
