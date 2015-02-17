package edu.cmu.lti.bic.sbs.simulator;


import java.util.List;
import java.util.Map;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.OxygenMask;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

//communicate with engine, just like the controller of patient
public class Simulator {

	public static void main(String[] args) {
		System.out.println(ytFunction(80));
	}
	Patient patient;

	// adding default value for four parameter
	float defaultBp;
	float defaultHr;
	final static Double defaultOl = 0.6;
	float defaultRr;

	// the initialization function for engine to involve
	public Patient initialPatient() {
		patient = new Patient();
		// pt.getBloodPressure().setBpNum(defaultBp);
		patient.getBloodPressure().setSystolicBloodPressure(90.0);
		patient.getBloodPressure().setDiastolicBloodPressure(60.0);
		patient.getHeartRate().setHrNum(80.0);
		patient.getOxygenLevel().setOlNum(99.0);
		patient.getRepiratinoRate().setRrNum(16.0);

		return patient;
	}

	public Simulator() {
		super();
		initialPatient();
	}

	public Simulator(Patient pt) {
		super();
		this.patient = pt;
	}

	//the engine can get patient info from simulator
	public Patient simPatient() {
		return patient;
	}

	
	public void setPt(Patient pt) {
		this.patient = pt;
	}
	
//	public void simulateWithTool(List<Tool> toolList){
//		//set the parameters according to the equipment from engine
//		
//		System.out.println("using equipments in the function simulateWithTool");
////		System.out.println(eq.getDescription());
////		System.out.println(eq.getId());
////		System.out.println(eq.getName());
//	}
//	
//	public void simWithDrugs(List<Drug> drugList){
//		//set the parameters according to the drug from engine
//		
//		System.out.println("using drug in the function simulateWithDrug");
////		System.out.println(drug.getDescription());
////		System.out.println(drug.getId());
////		System.out.println(drug.getName());
//		

	public void simulateWithTool(Tool eq) {
		// set the parameters according to the equipment from engine
		System.out.println("using equipments in the function simulateWithTool");
	}

	public void simWithDrugs(Prescription p) {
		// set the parameters according to the drug from engine
		System.out.println("using drug in the function simulateWithDrug");
	}
	
	public Patient updatePatient(List<Tool> toolList,  List<Drug> drugList){
		
		Tool currentTool = toolList.get(toolList.size() - 1);
		
		double value = currentTool.getValue();
		
		patient.setOxygenLevel(new OxygenLevel(ytFunction(value)));
		
		if(currentTool.getName() == "oxygenMask"){
			OxygenMask currentToolOxygenMask = (OxygenMask) currentTool;
			
			//System.out.println("id:"+currentToolOxygenMask.getId()+" name:"+currentToolOxygenMask.getName()+" description "+currentToolOxygenMask.getDescription() + " oxygenLevel "+currentToolOxygenMask.getOxygenValue());
		}
		
		System.out.println("invoke function simWithTool");
		//simulateWithTool(toolList);
		
		Drug currentDrug = drugList.get(drugList.size() - 1);
		
		System.out.println("id "+currentDrug.getId()+" name "+currentDrug.getName()+" description "+currentDrug.getDescription());
		
		System.out.println("invoke function simWithDrugs");
		//simWithDrugs(drugList);
		
		System.out.println("return patient");
		return patient;
	}
	
	//
	public static double fFunction(double x){
		double p1 = 1.667 * Math.pow(10, -6);
		double p2 = -0.0002536;
		double p3 = 0.01458;
		double p4 = -0.2743;
		
		double result;
		
		result = p1*Math.pow(x, 3) + p2*Math.pow(x, 2) + p3*x + p4;
		
		System.out.println("fFunction's result:"+result);
		
		return result;
	}
	
	//
	public static double t0Function(double x0){
		double result;
		
		result = 1.0*(120 - x0)/2 - 1.0/fFunction(x0) * Math.log(1.0*(1-defaultOl) / defaultOl);
	
		System.out.println("1.0*(120 - x0)/2 = " + 1.0*(120 - x0)/2);
		System.out.println("1.0/fFunction(x0) = "+1.0/fFunction(x0));
		System.out.println("Math.log(1.0*(1-defaultOl) / defaultOl = " + Math.log(1.0*(1-defaultOl) / defaultOl ));
		
		System.out.println("t0Function's result:"+result);
		
		return result;
	}
	
	//
	public static double ytFunction(double x0){
		double result;
		
		result = 1.0/(1 + Math.exp(-fFunction(x0) * ((t0Function(x0) + 1) - 1.0*(120 - x0)/2)));
		
		System.out.println("Math.exp(-fFunction(x0) * (t0Function(x0) + 1) - 1.0*(120 - x0)/2) ="+Math.exp(-fFunction(x0) * (t0Function(x0) + 1) - 1.0*(120 - x0)/2));
		System.out.println("");
		
		System.out.println("ytFunction's result:"+result);
		
		return result;
	}
	
	
	
}
