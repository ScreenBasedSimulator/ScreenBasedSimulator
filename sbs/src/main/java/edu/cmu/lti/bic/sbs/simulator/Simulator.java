package edu.cmu.lti.bic.sbs.simulator;

import java.util.List;
import java.util.Map;

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
	
	public void simulateWithTool(List<Tool> toolList){
		//set the parameters according to the equipment from engine
		
		System.out.println("using equipments in the function simulateWithTool");
//		System.out.println(eq.getDescription());
//		System.out.println(eq.getId());
//		System.out.println(eq.getName());

	}
	
	
	public void simWithDrugs(List<Drug> drugList){
		//set the parameters according to the drug from engine
		
		System.out.println("using drug in the function simulateWithDrug");
//		System.out.println(drug.getDescription());
//		System.out.println(drug.getId());
//		System.out.println(drug.getName());
		
	}
	
	public Patient updatePatient(List<Tool> toolList,  List<Drug> drugList){
		
		Tool currentTool = toolList.get(toolList.size() - 1);
		
		if(currentTool.getName() == "oxygenMask"){
			OxygenMask currentToolOxygenMask = (OxygenMask) currentTool;
			
			System.out.println("id:"+currentToolOxygenMask.getId()+" name:"+currentToolOxygenMask.getName()+" description "+currentToolOxygenMask.getDescription() + " oxygenLevel "+currentToolOxygenMask.getOxygenValue());

		}
		
		
		System.out.println("invoke function simWithTool");
		simulateWithTool(toolList);
		
		Drug currentDrug = drugList.get(drugList.size() - 1);
		
		System.out.println("id "+currentDrug.getId()+" name "+currentDrug.getName()+" description "+currentDrug.getDescription()+" dose "+currentDrug.getDoes());
		
		System.out.println("invoke function simWithDrugs");
		simWithDrugs(drugList);
		
		
		System.out.println("return patient");
		return pt;
		
	}
}
