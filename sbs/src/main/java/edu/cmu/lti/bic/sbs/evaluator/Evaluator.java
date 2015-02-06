package edu.cmu.lti.bic.sbs.evaluator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

public class Evaluator {
	private float score;
	private Engine engine;
	// private String report;
	
	class Report{
		double score;
		String report;
	}
	
	/** called by engine to receive the medPara
	 * 
	 * @param medPara, MedicalParameter is an interface in simulator package
	 */
	public void receivePara(MedicalParameter medPara){
	  System.out.println("evaluator.ReceivePara called by engine!");
	}
	

	/**
	 * called by engine to receive the drug and dose variables
	 * @param p Drug is a Class defined in gson package
	 * @param time time used
	 */
	public void receive(Prescription p, Calendar time){
	   System.out.println("Evaluator: USER ACTION: USE DRUG:" + p.getDrug().getName());
	}
	
	
	public void regularUpdate(Patient p, Calendar time) {
		
	}
	
	/**
	 * called by engine to receive the Equipment variables
	 * @param tool Equipment is a Class defined in gson package
	 * @param time time used
	 */
	public void receive(Tool tool, Calendar time){
	   System.out.println("Evaluator: USER ACTION: USE DRUG:" + tool.getName());
  }
	
	public void calculateScore() {
		score++;
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
		//eva.receive(new Prescription(new Drug(), 0.0, "test"));
		eva.calculateScore();
		eva.getScore();
		System.out.println(eva.toString());
		
	}
}
