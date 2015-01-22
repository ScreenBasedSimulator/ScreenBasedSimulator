package edu.cmu.lti.bic.sbs.simulator;

import edu.cmu.lti.bic.sbs.gson.*;

//communicate with engine, just like the controller of patient
public class Simulator {
	Patient pt;

	
	public Simulator(Patient pt) {
		super();
		this.pt = pt;
	}

	//
	public Patient simPatient() {
		return pt;
	}

	public void setPt(Patient pt) {
		this.pt = pt;
	}
	
	public void simEquipments(Equipment eq){
		//set the parameters according to the equipment from engine
	}
	
	
	public void simWithDrugs(Drug drug , float does ){
		//set the parameters according to the drug from engine
	}
}
