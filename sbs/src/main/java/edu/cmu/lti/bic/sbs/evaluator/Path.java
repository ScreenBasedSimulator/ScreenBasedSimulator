package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author victorzhao, xings, Ryan Sun
 *
 */
public class Path extends ArrayList<Step> {
  String tag = ""; // tag the type of this path: goldStandard or actual

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
        res -= (temp.stepPatientScore() + prev.stepPatientScore())
                * (temp.getTime().getTimeInMillis() - prev.getTime().getTimeInMillis()) * 0.0000001;
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

}
