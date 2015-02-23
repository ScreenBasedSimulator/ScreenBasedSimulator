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

  /**
   * @return the medPara
   */
  public MedicalParameter getMedPara() {
    return medPara;
  }

  /**
   * @param medPara
   *          the medPara to set
   */
  public void setMedPara(MedicalParameter medPara) {
    this.medPara = medPara;
  }

  /**
   * @return the drug
   */
  public Drug getDrug() {
    return drug;
  }

  /**
   * @param drug
   *          the drug to set
   */
  public void setDrug(Drug drug) {
    this.drug = drug;
  }

  /**
   * @return the dose
   */
  public double getDose() {
    return dose;
  }

  /**
   * @param dose
   *          the dose to set
   */
  public void setDose(double dose) {
    this.dose = dose;
  }

  /**
   * @return the tool
   */
  public Tool getTool() {
    return tool;
  }

  /**
   * @param tool
   *          the tool to set
   */
  public void setTool(Tool tool) {
    this.tool = tool;
  }

}
