package edu.cmu.lti.bic.sbs.evaluator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;
import edu.cmu.lti.bic.sbs.simulator.MedicalParameter;

// Test class
class BloodPressure implements MedicalParameter {

}

/**
 * 
 * @author victorzhao, xings, ryan sun
 *
 */
public class Evaluator {

  private double score;

  private Path actual;

  private Path goldStandard;

  private Step currentStep;

  private Engine engine;

  // private String report;
  public Evaluator() {
    actual = new Path();
    goldStandard = new Path();
    currentStep = new Step();
    actual.setTag("Actual");
    goldStandard.setTag("Gold Standard");
    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("0", "Call Code", ""),
            Calendar.getInstance()));
    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("1", "Mask", ""),
            Calendar.getInstance()));
    goldStandard.add(new Step(new Patient(), new Prescription(new Drug("1stDrug", "", "1"), 1.0,
            "L"), new Tool(), Calendar.getInstance()));
  }

  class Report {
    double score;

    String report;
  }

  /**
   * called by engine to receive the medPara
   * 
   * @param medPara
   *          , MedicalParameter is an interface in simulator package
   */
  public void receivePara(MedicalParameter medPara) {
    System.out.println("evaluator.ReceivePara called by engine!");
  }

  public void receive(Patient patient, Calendar time) {
    currentStep.setPatient(patient);
    currentStep.setTime(time);
    System.out.println("Patient added");
    updateStep();
  }

  public void receive(Prescription prescription, Calendar time) {
    currentStep.setPrescription(prescription);
    currentStep.setTime(time);
    System.out.println("Evaluator: USER ACTION: USE DRUG:" + prescription.getDrug().getName());
    updateStep();
  }

  // private String report;

  // overloading the constructor to support initialize with engine parameter
  public Evaluator(Engine engine) {
    this.engine = engine;
    actual = new Path();
    goldStandard = new Path();
    currentStep = new Step();
    actual.setTag("Actual");
    goldStandard.setTag("Gold Standard");
    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("0", "Call Code", ""),
            Calendar.getInstance()));
    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("1", "Mask", ""),
            Calendar.getInstance()));
    goldStandard.add(new Step(new Patient(), new Prescription(new Drug("1stDrug", "", "1"), 1.0,
            "L"), new Tool(), Calendar.getInstance()));
  }

  /**
   * called by engine to receive the drug and dose variables
   *
   * @param p
   *          Drug is a Class defined in gson package
   * @param time
   *          time used
   */

  public void updateStep() {
    if (currentStep.isComplete()) {
      actual.add(currentStep);
      currentStep = new Step();
    }
  }

  public void regularUpdate(Patient p, Calendar time) {

  }

  /**
   * called by engine to receive the Equipment variables
   * 
   * @param tool
   *          Equipment is a Class defined in gson package
   * @param time
   *          time used
   */

  public void receive(Tool tool, Calendar time) {
    currentStep.setTool(tool);
    currentStep.setTime(time);
    System.out.println("Evaluator: USER ACTION: USE DRUG:" + tool.getName());
    updateStep();
  }

  public void receive(Calendar time) {
    currentStep.setTime(time);
    System.out.println("Evaluator: USER ACTION: TIME:" + time.toString());
    updateStep();
  }

  public double getScore() {
    return score;
  }

  public String toString() {
    return "The score is " + score;
  }

  public void setInitialTime(Calendar initTime) {

  }

  public void calculateScore() {
    score = goldStandard.pathScore(actual);
    generateReport();
  }

  private void generateReport() {
    PrintWriter writer = null;
    try {
      writer = new PrintWriter("src/test/resources/report.json", "UTF-8");
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Gson gson = new Gson();
    Report r = new Report();
    r.report = this.toString();
    r.score = this.score;
    String report = gson.toJson(r);
    writer.println(report);
    writer.close();
  }

  // Main method for testing
  public static void main(String[] args) {
    Engine en = null;
    try {
      en = new Engine();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    Evaluator eva = new Evaluator(en);
    eva.receivePara(new BloodPressure());
    eva.calculateScore();
    eva.getScore();
    System.out.println(eva.toString());

  }


}
