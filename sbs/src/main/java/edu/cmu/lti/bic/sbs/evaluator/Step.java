package edu.cmu.lti.bic.sbs.evaluator;

import java.util.Calendar;

<<<<<<< HEAD
/**
 * The Step Class
 * 
 * @author Victor Zhao
 * 
 */
public class Step {
    // private Medicine medUsed;

    private Timer timeUsed;
    private Tool toolUsed;
    private Prescription prescriptionUsed;
    private Patient patient;

=======
import edu.cmu.lti.bic.sbs.gson.*;

/**
 * The Step Class
 * 
 * @author Victor Zhao, Xing Sun
 * 
 */
public class Step {

    // private Medicine medUsed;

    private Calendar timeUsed;
    private Tool toolUsed;
    private Prescription prescriptionUsed;
    private Patient patient;
    private StepRule stepRule;
>>>>>>> guanw
    // private undefined patientStatus;

    /**
     * 
     * @return The step description in serialize string.
     */
    public String getStep() {
<<<<<<< HEAD
	return prescriptionUsed.toString() + timeUsed.toString()
		+ toolUsed.toString();
=======
        return prescriptionUsed.toString() + timeUsed.toString() 
                + toolUsed.toString();
>>>>>>> guanw
    }

    public Step() {

    }

    /**
     * Step initializer
     * 
<<<<<<< HEAD
=======
     * Called when building the gold standard
     * 
>>>>>>> guanw
     * @param apatient
     *            The patient instance
     * @param prescription
     *            The prescription instance
     * @param tool
     *            The tool instance
     * @param time
     *            The time stamp
     */
    public Step(Patient apatient, Prescription prescription, Tool tool,
<<<<<<< HEAD
	    Timer time) {
	prescriptionUsed = prescription;
	timeUsed = time;
	toolUsed = tool;
	patient = apatient;
=======
	    Calendar time) {
        prescriptionUsed = prescription;
        timeUsed = time;
        toolUsed = tool;
        patient = apatient;
    }
    
    public Step(Patient apatient, Prescription prescription, Tool tool,
            Calendar time, String ruleFiles) {
              prescriptionUsed = prescription;
              timeUsed = time;
              toolUsed = tool;
              patient = apatient;
              stepRule = new StepRule(ruleFiles, this);
          }
    
    public void setRule(String ruleFiles){
      stepRule = new StepRule(ruleFiles, this);
>>>>>>> guanw
    }
    
    /**
     * The patient setter.
     * @param p The incoming patient instance
     */
    public void setPatient(Patient p) {
<<<<<<< HEAD
	patient = p;
=======
        patient = p;
>>>>>>> guanw
    }
    
    /**
     * The patient getter.
     * @return The patient instance
     */
    public Patient getPatient() {
<<<<<<< HEAD
	return patient;
=======
        return patient;
>>>>>>> guanw
    }
    
    /**
     * The prescription setter.
     * @param p incoming prescription instance
     */
    public void setPrescription(Prescription p) {
<<<<<<< HEAD
	prescriptionUsed = p;
=======
        prescriptionUsed = p;
>>>>>>> guanw
    }
    
    /**
     * prescription getter
     * @return the prescription within one step
     */
    public Prescription getPrescription() {
<<<<<<< HEAD
	return prescriptionUsed;
=======
        return prescriptionUsed;
>>>>>>> guanw
    }
    
    /** 
     * The tool setter
     * @param t incoming tool
     */
    public void setTool(Tool t) {
<<<<<<< HEAD
	toolUsed = t;
=======
        toolUsed = t;
>>>>>>> guanw
    }
    
    /**
     * The tool getter
     * @return the tool instance
     */
    public Tool getTool() {
<<<<<<< HEAD
	return toolUsed;
=======
        return toolUsed;
>>>>>>> guanw
    }
    
    /**
     * The time setter
     * @param t the incoming time instance
     */
<<<<<<< HEAD
    public void setTime(Timer t) {
	timeUsed = t;
=======
    public void setTime(Calendar t) {
        timeUsed = t;
>>>>>>> guanw
    }
    
    /**
     * The timer getter
     * @return the timer instance
     */
<<<<<<< HEAD
    public Timer getTime() {
	return timeUsed;
    }
    
    public boolean isComplete() {
	return (prescriptionUsed != null) && (patient != null)
=======
    public Calendar getTime() {
        return timeUsed;
    }
    
    public boolean isComplete() {
        return (prescriptionUsed != null) && (patient != null)
>>>>>>> guanw
		&& (toolUsed != null) && (timeUsed != null);
    }

    public double stepScore(Step a) {
<<<<<<< HEAD
	if (this.toolUsed == a.toolUsed
		&& this.prescriptionUsed == a.prescriptionUsed) {
	    return 1.0;
	} else {
	    return 0.0;
	}
    }

    public static void main(String[] args) {
	Step s = new Step();
	System.out.println(s.getStep());
	
=======
       if (stepRule == null){
        if (this.toolUsed == a.toolUsed
                && this.prescriptionUsed == a.prescriptionUsed) {
          return 1.0;
        } else {
          return 0.0;
        }
       }else{
         double score = stepRule.maxScore();
         
         return score;
       }
    }

    public static void main(String[] args) {
        Step s = new Step();
        System.out.println(s.getStep());
>>>>>>> guanw
    }
}
