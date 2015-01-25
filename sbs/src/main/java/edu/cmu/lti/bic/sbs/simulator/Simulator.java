package edu.cmu.lti.bic.sbs.simulator;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Tool;

//communicate with engine, just like the controller of patient
public class Simulator {
	Patient pt;
	
	//adding default value for four parameter
	float defaultBp;
	float defaultHr;
	float defaultOl;
	float defaultRr;

	
	//the initialization function for engine to involve
	public Patient initialPatient(){
		
		pt.getBloodPressure().setBpNum(defaultBp);
		pt.getHeartRate().setHrNum(defaultHr);
		pt.getOxygenLevel().setOlNum(defaultOl);
		pt.getRepiratinoRate().setRrNum(defaultRr);
		
		return pt;
		
	}
	
	public Simulator(){
		super();
	}
	
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
	
	public void simulateWithTool(Tool eq){
		//set the parameters according to the equipment from engine
		
	}
	
	
	public void simWithDrugs(Drug drug , float dose ){
		//set the parameters according to the drug from engine
		
	}
}
