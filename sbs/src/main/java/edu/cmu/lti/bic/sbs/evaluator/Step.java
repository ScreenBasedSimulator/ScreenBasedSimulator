package edu.cmu.lti.bic.sbs.evaluator;

import java.util.Calendar;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

/**
 * The Step Class
 * 
 * @author Victor Zhao, Xing Sun, Ryan Sun
 * 
 */
public class Step {

  // private Medicine medUsed;

  private int timeUsed = -1;

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
  public String getStep(boolean needTime) {
    System.out.println(timeUsed);
    StringBuilder output = new StringBuilder();
    if (needTime)
    	output.append(timeUsed/1000+"\t\t");
    output.append(prescriptionUsed.toString() + "\t\t" + toolUsed.toString() + "\n");
    return output.toString();
  }

  /**
   * 
   * default constructor
   * 
   */
  public Step() {

  
  }

  /**
   * 
   * Construtor copy another step
   * 
   * @param s
   * 
   */
  public Step(Step s) {
    this.timeUsed = s.getTime();
    this.toolUsed = s.getTool();
    this.prescriptionUsed = s.getPrescription();
    this.patient = s.getPatient();
    this.stepRule = s.getStepRule();
  }

  /**
   * Step initializer
   * 
   * Called when building the gold standard
   * 
   * @param apatient
   *          The patient instance
   * @param prescription
   *          The prescription instance
   * @param tool
   *          The tool instance
   * @param time
   *          The time stamp
   */
  public Step(Patient apatient, Prescription prescription, Tool tool, int time) {
    prescriptionUsed = prescription;
    timeUsed = time;
    toolUsed = tool;
    patient = apatient;
  }

  public Step(Patient apatient, Prescription prescription, Tool tool, int time,
          String ruleFiles) {
    prescriptionUsed = prescription;
    timeUsed = time;
    toolUsed = tool;
    patient = apatient;
    stepRule = new StepRule(ruleFiles, this);
  }

  public void setRule(String ruleFiles) {
    stepRule = new StepRule(ruleFiles, this);
  }

  /**
   * The patient setter.
   * 
   * @param p
   *          The incoming patient instance
   */
  public void setPatient(Patient p) {
    patient = p;
  }

  /**
   * The patient getter.
   * 
   * @return The patient instance
   */
  public Patient getPatient() {
    return patient;
  }

  /**
   * The prescription setter.
   * 
   * @param p
   *          incoming prescription instance
   */
  public void setPrescription(Prescription p) {
    prescriptionUsed = p;
  }

  /**
   * prescription getter
   * 
   * @return the prescription within one step
   */
  public Prescription getPrescription() {
    return prescriptionUsed;
  }

  /**
   * The tool setter
   * 
   * @param t
   *          incoming tool
   */
  public void setTool(Tool t) {
    toolUsed = t;
  }

  /**
   * The tool getter
   * 
   * @return the tool instance
   */
  public Tool getTool() {
    return toolUsed;
  }

  /**
   * The time setter
   * 
   * @param t
   *          the incoming time instance
   */
  public void setTime(int t) {
    timeUsed = t;
  }

  /**
   * The timer getter
   * 
   * @return the timer instance
   */
  public int getTime() {
    return timeUsed;
  }

  public boolean isComplete() {
    return (prescriptionUsed != null) && (patient != null) && (toolUsed != null)
            && (timeUsed != -1);
  }

  public double stepScore(Step a) {
    if (stepRule == null) {
      if (this.toolUsed.getId().equals(a.toolUsed.getId())
              && this.prescriptionUsed.getDrug().getId()
                      .equals(a.prescriptionUsed.getDrug().getId())) {
        double dosePenalty = 0.0;
        double timePenalty = 0.0;
        if (this.prescriptionUsed.getDose() != 0)
          dosePenalty = Math.abs(this.prescriptionUsed.getDose() - a.prescriptionUsed.getDose())
                  / this.prescriptionUsed.getDose();
        timePenalty = timeUsed - a.timeUsed;
        // if(dosePenalty>=1||timePenalty>=10000) return 0;
        return 1.0 * (1 - dosePenalty) * (1.0 - timePenalty / 10000);
      } else {
        return Integer.MIN_VALUE;
      }
    } else {
      double score = stepRule.maxScore();
      return score;
    }
  }
  
  public double stepPatientScore() {
    double res = 0.0;
    double oLpenalty = 1;
    double rRpenalty = 1;
    double bPpenalty = 1;
    double hRpenalty = 1;
    if (stepRule != null) {
      // add code here
    }
    double oL = patient.getOxygenLevel().getOlNum() - 80;
    if (oL < 0) {
      res += oL * oLpenalty;
    }
    double rR = Math.max(12.0 - patient.getRepirationRate().getRrNum(), patient.getRepirationRate()
            .getRrNum() - 20.0);
    if (rR > 0) {
      res -= rR * rRpenalty;
    }
    double bP = Math.max(patient.getBloodPressure().getDiastolicBloodPressure() - 100
            + patient.getBloodPressure().getSystolicBloodPressure() - 160, 140
            - patient.getBloodPressure().getDiastolicBloodPressure()
            - patient.getBloodPressure().getSystolicBloodPressure());
    if (bP > 0) {
      res -= bP * bPpenalty;
    }
    double hR = Math.max(patient.getHeartRate().getHrNum() - 100, 60 - patient.getHeartRate()
            .getHrNum());
    if (hR > 0) {
      res -= hR * hRpenalty;
    }

    return res;

  }

  public static void main(String[] args) {
    Step s = new Step(new Patient(), new Prescription(new Drug(), 10.0, "ml"), new Tool("0",
            "Call Code", 0), (int)Calendar.getInstance().getTimeInMillis());
    Step a = new Step(new Patient(), new Prescription(new Drug(), 20.0, "ml"), new Tool("0",
            "Call Code", 0), (int)Calendar.getInstance().getTimeInMillis());
    System.out.println(s.stepScore(a));

  }

  /**
   * @return the timeUsed
   */
  public int getTimeUsed() {
    return timeUsed;
  }

  /**
   * @param timeUsed
   *          the timeUsed to set
   */
  public void setTimeUsed(int timeUsed) {
    this.timeUsed = timeUsed;
  }

  /**
   * @return the toolUsed
   */
  public Tool getToolUsed() {
    return toolUsed;
  }

  /**
   * @param toolUsed
   *          the toolUsed to set
   */
  public void setToolUsed(Tool toolUsed) {
    this.toolUsed = toolUsed;
  }

  /**
   * @return the prescriptionUsed
   */
  public Prescription getPrescriptionUsed() {
    return prescriptionUsed;
  }

  /**
   * @param prescriptionUsed
   *          the prescriptionUsed to set
   */
  public void setPrescriptionUsed(Prescription prescriptionUsed) {
    this.prescriptionUsed = prescriptionUsed;
  }

  /**
   * @return the stepRule
   */
  public StepRule getStepRule() {
    return stepRule;
  }

  /**
   * @param stepRule
   *          the stepRule to set
   */
  public void setStepRule(StepRule stepRule) {
    this.stepRule = stepRule;
  }
}
