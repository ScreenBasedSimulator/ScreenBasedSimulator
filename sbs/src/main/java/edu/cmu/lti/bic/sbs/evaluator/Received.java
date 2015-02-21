package edu.cmu.lti.bic.sbs.evaluator;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.MedicalParameter;

public class Received {
  private MedicalParameter medPara;

  // I suggest to store drug with dose as a single class.
  private Drug drug;

  private double dose;

  private Tool tool;

  public void setPara(MedicalParameter medPara) {
    this.medPara = medPara;
  }

  public void set(Drug drug, double dose) {
    this.drug = drug;
    this.dose = dose;
  }

  public void set(Tool eq) {
    this.tool = eq;
  }

  public MedicalParameter getPara() {
    return medPara;
  }

  public Drug getDrug() {
    return drug;
  }

  public double getDose() {
    return dose;
  }

  public Tool getTool() {
    return tool;
  }
}
