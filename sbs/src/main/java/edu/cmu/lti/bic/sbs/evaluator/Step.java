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
     * 
     */
    public String getStep() {
        return prescriptionUsed.toString() + timeUsed.toString() 
                + toolUsed.toString();
    }

    
    /**
     * Default constructor
     * all of the attributes are null
     */
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
    
    /**
     * 
     * Called when ruleFiles are specified in goldstandard
     * 
     * @param apatient
     *          The patient instance
     * @param prescription
     *          The prescription instance
     * @param tool
     *          The tool instance
     * @param time
     *          The time stamp
     * @param ruleFiles
     *          the rule related to this step
     * 
     */
    public Step(Patient apatient, Prescription prescription, Tool tool,
            Calendar time, String ruleFiles) {
              prescriptionUsed = prescription;
              timeUsed = time;
              toolUsed = tool;
              patient = apatient;
              stepRule = new StepRule(ruleFiles, this);
          }
    
    /**
     * The rule setter
     * @param ruleFiles
     *          the rule related to this step
     */
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
    
    /**
     * check the step is complete
     * @return true if prescription, patient, tool and time are not null
     */
    public boolean isComplete() {
        return (prescriptionUsed != null) && (patient != null)
		&& (toolUsed != null) && (timeUsed != null);
    }

    /**
     * 
     * compare this step to the gold standard step
     * @param a the gold standard step
     * @return the step score as compared to step a
     * 
     */
    public double stepScore(Step a) {
      if (stepRule == null){
        if (this.toolUsed.getId().equals(a.toolUsed.getId())
                && this.prescriptionUsed.getDrug().getId().equals(a.prescriptionUsed.getDrug().getId())) {
          double dosePenalty = 0.0;
          double timePenalty = 0.0;
          if(this.prescriptionUsed.getDose()!=0)
            dosePenalty = Math.abs(
                    this.prescriptionUsed.getDose()-a.prescriptionUsed.getDose())
                    /this.prescriptionUsed.getDose();
          timePenalty = this.timeUsed.getTimeInMillis()-a.timeUsed.getTimeInMillis();
          //if(dosePenalty>=1||timePenalty>=10000) return 0;
          return 1.0*(1-dosePenalty)*(1.0-timePenalty/10000);
        } else {
          return 0.0;
        }
       }else{
         double score = stepRule.maxScore();
         
         return score;
       }
    }
    
    /**
     * 
     * get the patient score in this step
     * @return the patient score
     * 
     */
    public double stepPatientScore(){
      double res = 0.0;
      double oLpenalty = 0.1;
      if(stepRule == null){
        double oL = patient.getOxygenLevel().getOlNum()-80;
        if (oL < 0){
          res -= oL * oLpenalty;
        }
      }else{
        
      }
      return res;
      
    }

    /**
     * test cases
     */
    public static void main(String[] args) {
        Step s = new Step(new Patient(), new Prescription(new Drug(), 10.0, "ml"), new Tool("0", "Call Code", ""),
                Calendar.getInstance());
        Step a = new Step(new Patient(), new Prescription(new Drug(), 20.0, "ml"), new Tool("0", "Call Code", ""),
                Calendar.getInstance());
        System.out.println(s.stepScore(a));
        
    }
}
