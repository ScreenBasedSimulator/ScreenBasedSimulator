package edu.cmu.lti.bic.sbs.evaluator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.MedicalParameter;

// Test class
class BloodPressure implements MedicalParameter {

}

/**
 * 
 * @author victorzhao, xings, ryan sun
 *
 */
public class Evaluator {
	private double score;
	private Path actual;
	private Path goldStandard;
	private Step currentStep;
	private ScoreDP scoreDP;

	// private String report;
	public Evaluator(Engine engine) {
		this.engine = engine;
		actual = new Path();
		currentStep = new Step();
		actual.setTag("Actual");
		initGoden();
		scoreDP = new ScoreDP();
	}
	  
	  /**
	   * Initialize Golden Standard Path
	   * 
	   * @author Ryan
	   *
	   */
	
	private void initGoden(){
	   goldStandard = new Path();
	   goldStandard.setTag("Gold Standard");
	    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("codeblue",
	        "Call Code", ""), Calendar.getInstance()));
	    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("OxygenMask",
	        "Face Mask", ""), Calendar.getInstance()));
	    goldStandard.add(new Step(new Patient(), new Prescription(new Drug(
	        "naloxone", "Naloxone", "1"), 10.0, "ml"), new Tool(), Calendar.getInstance()));
	}

	class Report {
		double score;
		String report;
	}

	/**
	 * called by engine to receive the medPara
	 * 
	 * @param medPara
	 *          , MedicalParameter is an interface in simulator package
	 */
	public void receivePara(MedicalParameter medPara) {
		System.out.println("evaluator.ReceivePara called by engine!");
	}

	private Engine engine;

	// private String report;

	// overloading the constructor to support initialize with engine parameter

	public void receive(Patient patient, Calendar time) {
		currentStep.setPatient(patient);
		currentStep.setTime(time);
		System.out.println("Patient added");
		updateStep();
	}

	public void receive(Prescription prescription, Calendar time) {
		currentStep.setPrescription(prescription);
		currentStep.setTime(time);
		System.out.println("Evaluator: USER ACTION: USE DRUG:"
				+ prescription.getDrug().getName());
		updateStep();
	}

	/**
	 * called by engine to receive the Equipment variables
	 * 
	 * @param tool
	 *          Equipment is a Class defined in gson package
	 * @param time
	 *          time used
	 */

	public void receive(Tool tool, Calendar time) {
		currentStep.setTool(tool);
		currentStep.setTime(time);
		System.out.println("Evaluator: USER ACTION: USE DRUG:" + tool.getName());
		updateStep();
	}

	public void receive(Calendar time) {
		currentStep.setTime(time);
		updateStep();

	}

	public void regularUpdate(Patient p, Calendar time) {
		currentStep.setPatient(p);
		if (isSimEnd()) {
			calculateScore();
			engine.simOver(score, generateReport());
		}
	}

	public boolean isSimEnd() {
		// long timeNow = currentStep.getTime().getTimeInMillis();
		// long timeLast = actual.get(actual.size()-1).getTime().getTimeInMillis();
		// Patient p = currentStep.getPatient();
		// return 10000 < timeNow-timeLast &&
		// (p.getOxygenLevel().getOlNum() < .50 ||
		// p.getOxygenLevel().getOlNum()>.90);
		return actual.size() == 3;
	}

	/**
	 * called by engine to receive the Equipment variables
	 *
	 * @param tool
	 *          Equipment is a Class defined in gson package
	 * @param time
	 *          time used
	 */

	public void calculateScore() {
		score = scoreDP.scoreDP(goldStandard, actual);
	}
	
	public void calculateScorePending(){
	  score = scoreDP.scoreDPpending(goldStandard, actual);
	}

	public double getScore() {
		return score;
	}

	public void updateStep() {
		if (currentStep.isComplete()) {
			actual.add(currentStep);
			currentStep = new Step();
		}
	}

	public String toString() {
		return "The score is " + score;
	}

	public void setInitialTime(Calendar initTime) {

	}
	
	
	private String generateReport() {
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
		
		// Add the traceback information
		StringBuilder sb = new StringBuilder(report);
		sb.append("\n");
    sb.append("The user's correct actions are :" + "\n");

		for (Step s : scoreDP.getBacktrack()) {
		    sb.append(s.getStep());
//		    sb.append("\n");
		}
		System.out.println(sb.toString());
		// TODO: Set the patient score.
		// Where can I set the patient score??
		return sb.toString();
	}

	// Main method for testing
	public static void main(String[] args) {

	}
}
