package edu.cmu.lti.bic.sbs.gson;

public class Prescription {

  private Drug drug;

  private Double dose;

  private String unit;

  // Default initializer that will ensure testing successfully using main method.
  public Prescription() {
    this(new Drug(), 0.0, "Default unit");
  }

  public Prescription(Drug drug, double dose, String unit) {
    this.setDrug(drug);
    this.setDose(dose);
    this.setUnit(unit);
  }

  public String getUnit() {
    return unit;
  }

  public Prescription setUnit(String unit) {
    this.unit = unit;
    return this;
  }

  public Double getDose() {
    return dose;
  }

  public Prescription setDose(Double dose) {
    this.dose = dose;
    return this;
  }

  public Drug getDrug() {
    return drug;
  }

  public Prescription setDrug(Drug drug) {
    this.drug = drug;
    return this;
  }
}
