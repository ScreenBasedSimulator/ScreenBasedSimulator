package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author victorzhao, xings, Ryan Sun
 *
 */
public class Path extends ArrayList<Step> {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  String tag = ""; // tag the type of this path: goldStandard or actual

  double rrLowTime = 0;
  
  double rrHighTime = 0;
  
  double hrLowTime = 0;
  
  double bpLowTime = 0;
  
  double hrHighTime = 0;
  
  double bpHighTime = 0;
  
  double olTime = 0;
  
  /**
   * The method that use to calculate the score between two paths.
   * 
   * @param p2
   *          real path
   * @return score
   */
  public double pathScore(Path p2) throws NullPointerException {
    // Required this and p2
    double pScore = 0.0;
    Iterator<Step> itrThis;
    Iterator<Step> itrP2;
    try {
      itrThis = this.iterator();
      itrP2 = p2.iterator();
    } catch (NullPointerException e) {
      throw new NullPointerException();
    }
    while (itrP2.hasNext() && itrThis.hasNext()) {
      pScore += itrThis.next().stepScore(itrP2.next());
    }
    return pScore;
  }
  
  /**
   * 
   * Update the patient record
   * 
   * @param s
   *  the step to be added
   *  
   * @return
   *  whether it is added successful
   * 
   */
  public boolean add(Step s){
    double prevTime = 0;
    if (!this.isEmpty()){
      prevTime = (double)this.get(this.size()-1).getTime()/1000.0;
    }
    double curTime = (double)s.getTime()/1000.0;
    double time = (curTime-prevTime)/2.0;
    // check oxygen level
    if (s.getPatient().getOxygenLevel().getOlNum() - 79 < 0) {
      olTime += time;
    }
    // check respiratory rate
    if (11.9 - s.getPatient().getRepirationRate().getRrNum()>0) {
      rrLowTime += time;
    }else if(20.1 - s.getPatient().getRepirationRate().getRrNum()<0){
      rrHighTime += time;
    }
    // check blood pressure
    if (s.getPatient().getBloodPressure().getDiastolicBloodPressure() - 100
            + s.getPatient().getBloodPressure().getSystolicBloodPressure() - 160>0) {
      bpHighTime += time;
    }else if(140 
            - s.getPatient().getBloodPressure().getDiastolicBloodPressure() 
            - s.getPatient().getBloodPressure().getSystolicBloodPressure()>0){
      bpLowTime += time;
    }
    // check heart rate
    if (s.getPatient().getHeartRate().getHrNum() - 100 > 0) {
      hrHighTime += time;
    }else if(60 - s.getPatient().getHeartRate().getHrNum()>0){
      hrLowTime += time;
    }
    return super.add(s);
  }

  public double patientScore() {
    Iterator<Step> itrThis;
    Step prev = null;
    try {
      itrThis = this.iterator();
    } catch (NullPointerException e) {
      throw new NullPointerException();
    }
    double res = 100;

    while (itrThis.hasNext()) {
      Step temp = itrThis.next();
      if (prev == null) {
        res -= 0;
      } else {
        res += (temp.stepPatientScore() + prev.stepPatientScore())
                * (temp.getTime() - prev.getTime()) * 0.00001;
      }
      prev = temp;
    }
    return res;
  }

  public static void main(String[] args) {
    // The pathScore test case.
    // Due to the inefficient test case, calculating multiple scores
    // cannot be achieved.
    Path p1 = new Path();
    Path p2 = new Path();
    System.out.println("The score is " + p1.pathScore(p2));
    // TODO: Test scoring function locally inside Path class.
    // p1.add(new Step())

  }

  /**
   * @return the tag
   */
  public String getTag() {
    return tag;
  }

  /**
   * @param tag
   *          the tag to set
   */
  public void setTag(String tag) {
    this.tag = tag;
  }

  public double getRrLowTime() {
    return Math.round(rrLowTime);
  }

  public void setRrLowTime(double rrLowTime) {
    this.rrLowTime = rrLowTime;
  }

  public double getRrHighTime() {
    return Math.round(rrHighTime);
  }

  public void setRrHighTime(double rrHighTime) {
    this.rrHighTime = rrHighTime;
  }

  public double getHrLowTime() {
    return Math.round(hrLowTime);
  }

  public void setHrLowTime(double hrLowTime) {
    this.hrLowTime = hrLowTime;
  }

  public double getBpLowTime() {
    return Math.round(bpLowTime);
  }

  public void setBpLowTime(double bpLowTime) {
    this.bpLowTime = bpLowTime;
  }

  public double getHrHighTime() {
    return Math.round(hrHighTime);
  }

  public void setHrHighTime(double hrHighTime) {
    this.hrHighTime = hrHighTime;
  }

  public double getBpHighTime() {
    return Math.round(bpHighTime);
  }

  public void setBpHighTime(double bpHighTime) {
    this.bpHighTime = bpHighTime;
  }

  public double getOlTime() {
    return Math.round(olTime);
  }

  public void setOlTime(double olTime) {
    this.olTime = olTime;
  }

}
