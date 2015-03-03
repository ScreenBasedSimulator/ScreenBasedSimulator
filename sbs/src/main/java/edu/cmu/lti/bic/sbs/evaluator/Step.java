package edu.cmu.lti.bic.sbs.evaluator;

import java.util.Calendar;

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
    // private undefined patientStatus;

    /**
     * 
     * @return The step description in serialize string.
     */
    public String getStep() {
        return prescriptionUsed.toString() + timeUsed.toString() 
                + toolUsed.toString();
    }

    public Step() {

    }

    /**
     * Step initializer
     * 
     * Called when building the gold standard
     * 
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
    }
    
    /**
     * The patient setter.
     * @param p The incoming patient instance
     */
    public void setPatient(Patient p) {
        patient = p;
    }
    
    /**
     * The patient getter.
     * @return The patient instance
     */
    public Patient getPatient() {
        return patient;
    }
    
    /**
     * The prescription setter.
     * @param p incoming prescription instance
     */
    public void setPrescription(Prescription p) {
        prescriptionUsed = p;
    }
    
    /**
     * prescription getter
     * @return the prescription within one step
     */
    public Prescription getPrescription() {
        return prescriptionUsed;
    }
    
    /** 
     * The tool setter
     * @param t incoming tool
     */
    public void setTool(Tool t) {
        toolUsed = t;
    }
    
    /**
     * The tool getter
     * @return the tool instance
     */
    public Tool getTool() {
        return toolUsed;
    }
    
    /**
     * The time setter
     * @param t the incoming time instance
     */
    public void setTime(Calendar t) {
        timeUsed = t;
    }
    
    /**
     * The timer getter
     * @return the timer instance
     */
    public Calendar getTime() {
        return timeUsed;
    }
    
    public boolean isComplete() {
        return (prescriptionUsed != null) && (patient != null)
		&& (toolUsed != null) && (timeUsed != null);
    }

    public double stepScore(Step a) {
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
    }
}
