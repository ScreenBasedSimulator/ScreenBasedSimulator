package edu.cmu.lti.bic.sbs.simulator;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.OxygenMask;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

//communicate with engine, just like the controller of patient
public class Simulator {


	Patient patient;

	//
	ArrayList<Tool> toolList;
	ArrayList<Prescription> prescriptionList;

	// adding default value for four parameter


	// the initialization function for engine to involve
	public Patient initialPatient() {
		patient = new Patient();
		// pt.getBloodPressure().setBpNum(defaultBp);
		patient.getBloodPressure().setSystolicBloodPressure(90.0);
		patient.getBloodPressure().setDiastolicBloodPressure(60.0);
		patient.getHeartRate().setHrNum(80.0);
		patient.getOxygenLevel().setOlNum(0.6);
		patient.getRepiratinoRate().setRrNum(16.0);

		return patient;
	}

	public Simulator() {
		super();
		initialPatient();
		toolList = new ArrayList<Tool>();
		prescriptionList = new ArrayList<Prescription>();
	}

	public Simulator(Patient pt) {
		super();
		this.patient = pt;
		toolList = new ArrayList<Tool>();
		prescriptionList = new ArrayList<Prescription>();
	}

	//the engine can get patient info from simulator
	public Patient simPatient( ) {
		
		if(toolList.size() == 0 && prescriptionList.size() == 0){
			
			double resultOxygenLevel = downFunctionOxygenLevel();
			double resultHeartRate = downFunctionHeartRate();
			double resultSystolicBloodPressure = downFunctionBloodPressure();
			double resultDiastolicBloodPressure = downFunctionBloodPressure();
			double resultRespirationRate = downFunctionRespirationRate();
			
			return this.patient;
		}
		else{
			double currentOxygenLevel = patient.getOxygenLevel().getOlNum();
			double currentHeartRate = patient.getHeartRate().getHrNum();
			double currentSystolicBloodPressure = patient.getBloodPressure().getSystolicBloodPressure();
			double currentDiastolicBloodPressure = patient.getBloodPressure().getDiastolicBloodPressure();
			double currentRespirationRate = patient.getRepiratinoRate().getRrNum();
			
			//
			Tool currentTool = toolList.get(toolList.size() - 1);
			double currentValue = currentTool.getValue();
			
			//
			if(currentTool.getName().equals("OxygenMask")){
				double resultOl = ytFunctionOxygenLevel(currentValue);
				
				System.out.print("resultOL = " + resultOl);
				
				patient.setOxygenLevel(new OxygenLevel(resultOl));
			}
			
			//the name of the tool do not correct, I just test the function
			if(currentTool.getName().equals("increase the heart rate")){
				//
				double resultRatioForHeartRate = ytFunctionHeartRate(currentValue);
				
				System.out.print("resultHR = "+resultRatioForHeartRate );
				
				double resultHeartRate = (1+resultRatioForHeartRate/100) * currentHeartRate;
				
				System.out.print("patient's heart rate:" +  resultHeartRate);
				
				patient.setHeartRate(new HeartRate(resultHeartRate));
			}
			
			//the name of the tool do not correct, I just test the function
			if(currentTool.getName().equals("increase the blood pressure")){
						//
				double resultRatioForBloodPressure = ytFunctionBloodPressure(currentValue);
						
				System.out.print("resultBR = "+resultRatioForBloodPressure );
						
				double resultSystolicBloodPressure = (1+resultRatioForBloodPressure/100) * currentSystolicBloodPressure;
				double resultDiastolicBloodPressure = (1+resultRatioForBloodPressure/100) * currentDiastolicBloodPressure;
				
				System.out.print("patient's Systolic blood pressure:" +  resultSystolicBloodPressure);
				System.out.println("patient's Diastolic blood pressure:" +  resultDiastolicBloodPressure);
						
				patient.setBloodPressure(new BloodPressure(currentSystolicBloodPressure, currentDiastolicBloodPressure));
			}
			
			//the name of the tool do not correct, I just test the function
			if(currentTool.getName().equals("increase the Respiration Rate")){
				//
				double resultRatioForRespirationRate = ytFunctionRespirationRate(currentValue);
				
				System.out.print("resultRR = "+resultRatioForRespirationRate );
				
				double resultRespirationRate = (1 + resultRatioForRespirationRate/100) * currentRespirationRate;
				
				System.out.print("patient's Systolic Respiration Rate:" +  resultRespirationRate);
				
				patient.setRespirationRate(new RespirationRate(resultRespirationRate));
			}
			
			//get the current prescription
			Prescription currentPrescription = prescriptionList.get(prescriptionList.size() - 1);
			double currentDoes = currentPrescription.getDose();
			
			//
			if(currentPrescription.getDrug().getName().equals("OxygenMask")){
				double resultOl = ytFunctionOxygenLevel(currentDoes);
				
				System.out.print("resultOL = " + resultOl);
				
				patient.setOxygenLevel(new OxygenLevel(resultOl));
			}
			
			//the name of the tool do not correct, I just test the function
			if(currentPrescription.getDrug().getName().equals("increase the heart rate")){
				//
				double resultRatioForHeartRate = ytFunctionHeartRate(currentDoes);
				
				System.out.print("resultHR = "+resultRatioForHeartRate );
				
				double resultHeartRate = (1+resultRatioForHeartRate/100) * currentHeartRate;
				
				System.out.print("patient's heart rate:" +  resultHeartRate);
				
				patient.setHeartRate(new HeartRate(resultHeartRate));
			}
			
			//the name of the tool do not correct, I just test the function
			if(currentPrescription.getDrug().getName().equals("increase the blood pressure")){
						//
				double resultRatioForBloodPressure = ytFunctionBloodPressure(currentDoes);
						
				System.out.print("resultBR = "+resultRatioForBloodPressure );
						
				double resultSystolicBloodPressure = (1+resultRatioForBloodPressure/100) * currentSystolicBloodPressure;
				double resultDiastolicBloodPressure = (1+resultRatioForBloodPressure/100) * currentDiastolicBloodPressure;
				
				System.out.print("patient's Systolic blood pressure:" +  resultSystolicBloodPressure);
				System.out.println("patient's Diastolic blood pressure:" +  resultDiastolicBloodPressure);
						
				patient.setBloodPressure(new BloodPressure(currentSystolicBloodPressure, currentDiastolicBloodPressure));
			}
			
			//the name of the tool do not correct, I just test the function
			if(currentPrescription.getDrug().getName().equals("increase the Respiration Rate")){
				//
				double resultRatioForRespirationRate = ytFunctionRespirationRate(currentDoes);
				
				System.out.print("resultRR = "+resultRatioForRespirationRate );
				
				double resultRespirationRate = (1 + resultRatioForRespirationRate/100) * currentRespirationRate;
				
				System.out.print("patient's Systolic Respiration Rate:" +  resultRespirationRate);
				
				patient.setRespirationRate(new RespirationRate(resultRespirationRate));
			}
			
			return patient;
		}
		
		
		
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public ArrayList<Tool> getToolList() {
		return toolList;
	}

	public void setToolList(ArrayList<Tool> toolList) {
		this.toolList = toolList;
	}

	public ArrayList<Prescription> getPrescriptionList() {
		return prescriptionList;
	}

	public void setPrescriptionList(ArrayList<Prescription> prescriptionList) {
		this.prescriptionList = prescriptionList;
	}

	public void simulateWithTool(Tool eq) {
		// set the parameters according to the equipment from engine
		System.out.println("using equipments in the function simulateWithTool");
		
		//when the engine involve the function simulateWithTool, the simulator add the tool to the toolList
		toolList.add(eq);
	}

	public void simWithDrugs(Prescription p) {
		// set the parameters according to the drug from engine
		System.out.println("using drug in the function simulateWithDrug");
		
		//
		prescriptionList.add(p);
	}
	
	//maybe this function is useless
	/*
	//the parameter is two list, toolList and drugList
	public Patient updatePatient(List<Tool> toolList,  List<Drug> drugList){
		
		Tool currentTool = toolList.get(toolList.size() - 1);
		
		double value = currentTool.getValue();
		
		patient.setOxygenLevel(new OxygenLevel(ytFunctionOxygenLevel(value)));
		
		if(currentTool.getName().equals("oxygenMask")){
			OxygenMask currentToolOxygenMask = (OxygenMask) currentTool;
			
			//System.out.println("id:"+currentToolOxygenMask.getId()+" name:"+currentToolOxygenMask.getName()+" description "+currentToolOxygenMask.getDescription() + " oxygenLevel "+currentToolOxygenMask.getOxygenValue());
		}
		
		System.out.println("invoke function simWithTool");
		
		Drug currentDrug = drugList.get(drugList.size() - 1);
		
		System.out.println("id "+currentDrug.getId()+" name "+currentDrug.getName()+" description "+currentDrug.getDescription());
		
		System.out.println("invoke function simWithDrugs");
		//simWithDrugs(drugList);
		
		System.out.println("return patient");
		return patient;
	}
	*/
	

		//helper function 
	double fFunction(double x){
		double p1 = 1.667 * Math.pow(10, -6);
		double p2 = -0.0002536;
		double p3 = 0.01458;
		double p4 = -0.2743;
		
		double result;
		
		result = p1*Math.pow(x, 3) + p2*Math.pow(x, 2) + p3*x + p4;
		
		return result;
	}
	

	//helper function
	public double t0Function(double x0){
		double result;
		
		result = 1.0*(120 - x0)/2 - 1.0/fFunction(x0) * Math.log(1.0*(1-patient.getOxygenLevel().getOlNum()) / //
				patient.getOxygenLevel().getOlNum());
	
		System.out.println("1.0*(120 - x0)/2 = " + 1.0*(120 - x0)/2);
		System.out.println("1.0/fFunction(x0) = "+1.0/fFunction(x0));
		System.out.println("Math.log(1.0*(1-defaultOl) / defaultOl = " + //
				Math.log(1.0*(1-patient.getOxygenLevel().getOlNum()) / patient.getOxygenLevel().getOlNum()));
		
		System.out.println("t0Function's result:"+result);
		
		return result;
	}
	
	//this function calculate the curve(oxygen level)
	public double ytFunctionOxygenLevel(double x0){
		double result;
		
		result = 1.0/(1 + Math.exp(-fFunction(x0) * ((t0Function(x0) + 1.0/10) - 1.0*(120 - x0)/2)));
		
		return result;
	}
	
	
	//this function calculate the curve(BloodPressure)
	public double ytFunctionBloodPressure(double x0){
	
		double result;
		
		result = 1.0/(1 + Math.exp(-fFunction(x0) * ((t0Function(x0) + 1.0/15) - 1.0*(120 - x0)/2)));
			
		return result;
	}
	
	//this function calculate the curve(HeartRate)
	public double ytFunctionHeartRate(double x0){
		
		double result;
			
		result = 1.0/(1 + Math.exp(-fFunction(x0) * ((t0Function(x0) + 1.0/17) - 1.0*(120 - x0)/2)));
				
		return result;
	}	
	
	//this function calculate the curve(RespirationRate)
	public double ytFunctionRespirationRate(double x0){
		
		double result;
			
		result = 1.0/(1 + Math.exp(-fFunction(x0) * ((t0Function(x0) + 1.0/8) - 1.0*(120 - x0)/2)));
				
		return result;
	}
	
	
	
	//invoke the function OxygenLevel when there is no action
	public double downFunctionOxygenLevel(){
		double result;
		
		result = 1 - 0.001 * Math.pow(1, 2);
		
		double currentOxygenLevel = patient.getOxygenLevel().getOlNum();
		
		//
		this.patient.setOxygenLevel(new OxygenLevel(result * currentOxygenLevel));
		
		return result;
	}
	
	//invoke the function BloodPressure when there is no action
	public double downFunctionBloodPressure(){
		double result;
			
		result = 1 - 0.001 * Math.pow(1, 2);
			
		double currentSystolicBloodPressure = patient.getBloodPressure().getSystolicBloodPressure();
		double currentDiastolicBloodPressure = patient.getBloodPressure().getDiastolicBloodPressure();
		
		System.out.println("currentSystolicBloodPressure = " + currentSystolicBloodPressure);
		System.out.println("currentDiastolicBloodPressure = " + currentDiastolicBloodPressure);
		
		this.patient.setBloodPressure(new BloodPressure(currentSystolicBloodPressure * result, currentDiastolicBloodPressure * result));
		
		return result;
	}
	
	//invoke the function HeartRate when there is no action
	public double downFunctionHeartRate(){
	
		double result;
	
		result = 1 - 0.0005 * Math.pow(1, 2);
		
		double currentHeartRate = patient.getHeartRate().getHrNum();
		
		this.patient.setHeartRate(new HeartRate(result * currentHeartRate));
				
		return result;
	}
	
	//invoke the function RespirationRate when there is no action
	public double downFunctionRespirationRate(){
		
		double result;
		
		result = 1 - 0.0008 * Math.pow(1, 2);
		
		double currentRespirationRate = patient.getRepiratinoRate().getRrNum();
		
		this.patient.setRespirationRate(new RespirationRate(result * currentRespirationRate));
		
		return result;
	}
	
	
}
