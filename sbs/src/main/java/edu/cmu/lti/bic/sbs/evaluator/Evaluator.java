package edu.cmu.lti.bic.sbs.evaluator;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

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

  private ScoreDP scoreDP;

  private long baseTimeInMills = 0;
  
  // private String report;
  public Evaluator(Engine engine) {
    this.engine = engine;
    actual = new Path();
    currentStep = new Step();
    actual.setTag("Actual");
    initGoden();
    scoreDP = new ScoreDP();
    baseTimeInMills = Calendar.getInstance().getTimeInMillis();
  }

  /**
   * Initialize Golden Standard Path
   * 
   */

  private void initGoden() {
    goldStandard = new Path();
    goldStandard.setTag("Gold Standard");
    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("codeblue", "Call Code",
            ""), (int)Calendar.getInstance().getTimeInMillis()));
    goldStandard.add(new Step(new Patient(), new Prescription(), new Tool("OxygenMask",
            "Face Mask", ""), (int)Calendar.getInstance().getTimeInMillis()));
    goldStandard
            .add(new Step(new Patient(), new Prescription(new Drug("naloxone", "Naloxone", "1"),
                    10.0, "ml"), new Tool(), (int)Calendar.getInstance().getTimeInMillis()));
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

  private Engine engine;

  // private String report;

  // overloading the constructor to support initialize with engine parameter

  public void receive(Patient patient, Calendar time) {
    currentStep.setPatient(patient);
    int curTime = (int)(time.getTimeInMillis() - baseTimeInMills);
    currentStep.setTime(curTime);
    System.out.println("Patient added");
    updateStep();
  }

  public void receive(Prescription prescription, Calendar time) {
    currentStep.setPrescription(prescription);
    int curTime = (int)(time.getTimeInMillis() - baseTimeInMills);
    currentStep.setTime(curTime);
    System.out.println("Evaluator: USER ACTION: USE DRUG:" + prescription.getDrug().getName());
    updateStep();
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
    int curTime = (int)(time.getTimeInMillis() - baseTimeInMills);
    currentStep.setTime(curTime);
    System.out.println("Evaluator: USER ACTION: USE DRUG:" + tool.getName());
    updateStep();
  }

  public void receive(Calendar time) {
    int curTime = (int)(time.getTimeInMillis() - baseTimeInMills);
    currentStep.setTime(curTime);
    updateStep();

  }

  public void regularUpdate(Patient p, Calendar time) {
    currentStep.setPatient(p);
    if (isSimEnd()) {
      calculateScore();
      engine.simOver(score, generateReport());
    }
  }

  public boolean isSimEnd() {
    if (actual.size()==0) return false;
     int timeNow = currentStep.getTime();
     int timeLast = actual.get(actual.size()-1).getTime();
     Patient p = currentStep.getPatient();
     return 10000 < timeNow-timeLast ||
             (p.getOxygenLevel().getOlNum() < .50 ||
                     p.getOxygenLevel().getOlNum()>.90);
  }

  /**
   * called by engine to receive the Equipment variables
   *
   * @param tool
   *          Equipment is a Class defined in gson package
   * @param time
   *          time used
   */

  public void calculateScore() {
    score = scoreDP.scoreDP(goldStandard, actual);
  }

  public void calculateScorePending() {
    score = scoreDP.scoreDPpending(goldStandard, actual);
  }

  public double getScore() {
    return score;
  }
  
  public double getPatientScore(){
    return actual.patientScore();
  }

  public void updateStep() {
    if (currentStep.isComplete()) {
      actual.add(currentStep);
      currentStep = new Step();
    }
  }

  public String toString() {
    return "The score is " + score;
  }

  public void setInitialTime(Calendar initTime) {

  }

  private String generateReport() {
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

    // Add the traceback information
    StringBuilder sb = new StringBuilder(report);
    sb.append("\n");
    sb.append("Patient score is: ");
    sb.append(getPatientScore());
    sb.append("\n");
    sb.append("The user's correct actions are :" + "\n");

    for (Step s : scoreDP.getBacktrack()) {
      sb.append(s.getStep());
      // sb.append("\n");
    }
    System.out.println(sb.toString());
    
    
    // generate the txt report
    txtReportGenerator(score);
    
    
    // TODO: Set the patient score.
    // Where can I set the patient score??
    return sb.toString();
  }

  public static Path loadGS(String filepath) throws Exception{
      String str;
      try {
	  File file = new File(filepath);
	  FileInputStream fis = new FileInputStream(file);
	  byte[] data = new byte[(int) file.length()];
	  fis.read(data);
	  fis.close();
	  str = new String(data, "UTF-8");
      } catch (Exception e) {
	  throw e;
      }      
      
      Gson gson = new Gson();
      Path gs;
      try {
	  gs = gson.fromJson(str, Path.class);
      } catch (JsonSyntaxException e) {
	  throw new Exception(e);
      }
      return gs;
  }

  private void txtReportGenerator(double score){
    String outputFile = "Report.txt";
    String familyName = "Smith";
    String firstName = "John";
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, false));
      StringBuilder output = new StringBuilder();
      output.append("\nHere is the report for ");
      output.append(firstName + " " + familyName + ":" + "\n");
      output.append("\nThe final score " + firstName + " get is : " 
                      + String.format("%.2f\n\n", score));
      
      output.append("The helpful steps and details "  
                      + firstName + " did is listed below : \n\n");
      
      output.append("Action Time\t Drug Used\t\t Drug Dose\t Drug Unit\t\t    Action\n");
      
      for (Step s : scoreDP.getBacktrack()) {
        output.append(s.getStep());
        // sb.append("\n");
      }
      
      
      output.append("\n\n\nThe actual steps and details "  
              + firstName + " did is listed below : \n\n");

      output.append("Action Time\t Drug Used\t\t Drug Dose\t Drug Unit\t\t    Action\n");
      
      for (Step s : actual) {
        output.append(s.getStep());
        // sb.append("\n");
      }
      
      
      bw.write(output.toString());
      System.out.println(output);
      
      bw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  // Main method for testing
  public static void main(String[] args) {
      Gson gson = new Gson();
      ArrayList<Step> a = new ArrayList<Step>();
      a.add(new Step(new Patient(), new Prescription(), new Tool("codeblue", "Call Code",
	            ""), (int)Calendar.getInstance().getTimeInMillis()));
      a.add(new Step(new Patient(), new Prescription(), new Tool("codeblue", "Call Code",
	            ""), (int)Calendar.getInstance().getTimeInMillis()));
      a.add(new Step(new Patient(), new Prescription(), new Tool("codeblue", "Call Code",
	            ""), (int)Calendar.getInstance().getTimeInMillis()));
      System.out.println(gson.toJson(a));
  }
}
