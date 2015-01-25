package edu.cmu.lti.bic.sbs.evaluator;
 import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.MedicalParameter;
 
public class Evaluator {
	private float score;
	private Engine e;
	private String report;
	
	/** called by engine to receive the medPara
	 * 
	 * @param medPara, MedicalParameter is an interface in simulator package
	 */
	public void ReceivePara(MedicalParameter medPara){
	  System.out.println("evaluator.ReceivePara called by engine!");
	}
	
	/**
	 * called by engine to receive the drug and dose variables
	 * 
	 * @param drug, Drug is a Class defined in gson package
	 * @param dose
	 */
	public void Receive(Drug drug, double dose){
	   System.out.println("evaluator.Receive(Drug drug, double dose) called by engine!");
	}
	
	/**
	 *  called by engine to receive the Equipment variables
	 * 
	 * @param eq, Equipment is a Class defined in gson package
	 */
	
	public void Receive(Tool tool){
	   System.out.println("evaluator.ReceivePara(Equipment eq) called by engine!");
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
}
