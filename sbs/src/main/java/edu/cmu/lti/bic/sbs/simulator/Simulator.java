package edu.cmu.lti.bic.sbs.simulator;

import edu.cmu.lti.bic.sbs.gson.*;

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
		
		pt.getBp().setBpNum(defaultBp);
		pt.getHr().setHrNum(defaultHr);
		pt.getOl().setOlNum(defaultOl);
		pt.getRr().setRrNum(defaultRr);
		
		return pt;
		
	}
	
	public Simulator(){
		super();
	}
	
	public Simulator(Patient pt) {
		super();
		this.pt = pt;
	}

	//the engine can get patient info from simulator
	public Patient simPatient() {
		return pt;
	}

	
	public void setPt(Patient pt) {
		this.pt = pt;
	}
	
	public void simulateWithTool(Tool eq){
		//set the parameters according to the equipment from engine
		
		System.out.println("using equipments in the function simulateWithTool");
//		System.out.println(eq.getDescription());
//		System.out.println(eq.getId());
//		System.out.println(eq.getName());

	}
	
	
	public void simWithDrugs(Drug drug , float dose ){
		//set the parameters according to the drug from engine
		
		System.out.println("using drug in the function simulateWithDrug");
//		System.out.println(drug.getDescription());
//		System.out.println(drug.getId());
//		System.out.println(drug.getName());
		
	}
	
	public Patient updatePatient(Tool eq, Drug drug, float dose){
		
		System.out.println("id:"+eq.getId()+" name:"+eq.getName()+" description"+eq.getDescription());
		
		System.out.println("invoke function simWithTool");
		simulateWithTool(eq);
		
		
		System.out.println("id "+drug.getId()+" name "+drug.getName()+" description "+drug.getDescription()+" dose "+dose);
		
		System.out.println("invoke function simWithDrugs");
		simWithDrugs(drug, dose);
		
		
		System.out.println("return patient");
		return pt;
		
	}
}
