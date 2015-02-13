package edu.cmu.lti.bic.sbs.evaluator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.MedicalParameter;

// Test class
class BloodPressure implements MedicalParameter {

}

public class Evaluator {
	private float score;
	private Engine engine;
	private Path actual;
	private Path goldStandard;
	private Step currentStep;
	private Engine engine;
	// private String report;
	public Evaluator(){
	  actual = new Path();
	  goldStandard = new Path();
	  currentStep = new Step();
	  actual.setTag("Actual");
	  goldStandard.setTag("Gold Standard");
	  goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("0", "Call Code", ""), new Time()));
	  goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("1", "Mask", ""), new Time()));
	  goldStandard.add(new Step(new Patient(), new Prescription(new Drug("1stDrug", "", "1"), 1.0, "L"), new Tool(), new Time()));
	}
	
	class Report{
		double score;
		String report;
	}
	/** called by engine to receive the medPara
	 * 
	 * @param medPara
	 *          , MedicalParameter is an interface in simulator package
	 */
	public void receivePara(MedicalParameter medPara) {
		System.out.println("evaluator.ReceivePara called by engine!");
	}

	/**
	 * called by engine to receive the drug and dose variables
	 * 
	 * @param p
	 *          Drug is a Class defined in gson package
	 * @param time
	 *          time used
	 */
	
	public void updateStep(){
    if (currentStep.isComplete()){
      actual.add(currentStep);
      currentStep = new Step();
    }
	}
	
	public void receive(Patient patient){
	   currentStep.setPatient(patient);
	   System.out.println("Patient added");
	   updateStep();
	}
	
	public void receive(Prescription prescription){
	  currentStep.setPrescription(prescription);
	  System.out.println("Evaluator: USER ACTION: USE DRUG:" + p.getDrug().getName());
	  updateStep();
	}

	public void regularUpdate(Patient p, Calendar time) {

	}

	/**
	 * called by engine to receive the Equipment variables
	 * 
	 * @param tool
	 *          Equipment is a Class defined in gson package
	 * @param time
	 *          time used
	 */
	
	public void receive(Tool tool){
	  currentStep.setTool(tool);
	  System.out.println("Evaluator: USER ACTION: USE DRUG:" + tool.getName());
	  updateStep();
  }
	
	public void receive(Timer time){
	  currentStep.setTime(time);
	  System.out.println("Evaluator: USER ACTION: TIME:" + time.toString());
	  updateStep();
	}
	
	public void calculateScore() {
		score = goldStandard.pathScore(actual);
		generateReport();
	}

	public float getScore() {
		return score;
	}

	public String toString() {
		return "The score is " + score;
	}

	public Evaluator() {
		score = 0;
		paras = new ArrayList<Received>();
	}

	public void setInitialTime(Calendar initTime) {

	}

	private void generateReport() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("src/test/resources/report.json", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Report r = new Report();
		r.report = this.toString();
		r.score = this.score;
		String report = gson.toJson(r);
		writer.println(report);
		writer.close();
	}

	// Main method for testing
	public static void main(String[] args) {
		Evaluator eva = new Evaluator();
		eva.receivePara(new BloodPressure());
		eva.calculateScore();
		eva.getScore();
		System.out.println(eva.toString());

	}
}
