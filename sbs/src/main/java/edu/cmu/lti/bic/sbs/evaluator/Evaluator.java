package edu.cmu.lti.bic.sbs.evaluator;
 import java.util.ArrayList;

import edu.cmu.lti.bic.sbs.engine.engineControler;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Equipment;
import edu.cmu.lti.bic.sbs.simulator.MedicalParameter;
 
public class Evaluator {
	private float score;
	private engineControler e;
	private String report;
	
	// data structure to store the parameters recieved
	
	private ArrayList<Recieved> paras = new  ArrayList<Recieved>();
	
	private static class Recieved{
	  private MedicalParameter medPara;
	  // I suggest to store drug with dose as a single calss.
	  private Drug drug;
	  private double dose;
	  private Equipment eq;
	  
	  public void setPara(MedicalParameter medPara){
	    this.medPara = medPara;
	  }
	  
	   public void set(Drug drug, double dose){
	      this.drug = drug;
	      this.dose = dose;
	    }
	   public void set(Equipment eq){
	     this.eq = eq;
	   }
	}
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
	
	public void Receive(Equipment eq){
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
	
	Evaluator() {
		score = 0;
	}
}
