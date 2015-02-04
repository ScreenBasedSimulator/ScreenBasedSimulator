package edu.cmu.lti.bic.sbs.evaluator;
import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.MedicalParameter;

// Test class
class BloodPressure implements MedicalParameter {
	
}

public class Evaluator {
	private float score;
	private Engine e;
	private String report;
	
	/** called by engine to receive the medPara
	 * 
	 * @param medPara, MedicalParameter is an interface in simulator package
	 */
	public void receivePara(MedicalParameter medPara){
	  System.out.println("evaluator.ReceivePara called by engine!");
	}
	
	/**
	 * called by engine to receive the drug and dose variables
	 * 
	 * @param drug, Drug is a Class defined in gson package
	 * @param dose
	 */
	public void receive(Prescription p){
	   System.out.println("Evaluator: USER ACTION: USE DRUG:" + p.getDrug().getName());
	}
	
	/**
	 *  called by engine to receive the Equipment variables
	 * 
	 * @param eq, Equipment is a Class defined in gson package
	 */
	
	public void receive(Tool tool){
	   System.out.println("Evaluator: USER ACTION: USE DRUG:" + tool.getName());
  }
	
	public void calculateScore() {
		score++;
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
	
	// Main method for testing
	public static void main(String[] args) {
		Evaluator eva = new Evaluator();
		eva.receivePara(new BloodPressure());
		eva.receive(new Prescription(new Drug(), 0.0, "test"));
		eva.calculateScore();
		eva.getScore();
		System.out.println(eva.toString());
		
	}
}
