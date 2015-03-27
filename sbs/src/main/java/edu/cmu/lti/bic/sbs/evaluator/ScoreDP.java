<<<<<<< HEAD
package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;
import java.util.Calendar;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

/**
 *
 * This class, we try to use dynamic programming algorithm to implement the score generation
 * function for the Evaluator. This class, we try to use dynamic programming algorithm to implement
 * the score generation function for the Evaluator.
 *
 * @author Ryan Sun
 *
 */
public class ScoreDP {
  /**
   *
   * The matrix to store the immediate value of the DP algotithm
   *
   */
  private static double[][] matrix;

  private static Pair[][] path;

  /**
   *
   * ArrayList to store the backtrack path
   *
   */

  private static ArrayList<Step> backtrack;

  // private static double score;

  /**
   * constructor of the scoreDP class
   *
   */

  public ScoreDP() {
    backtrack = new ArrayList<Step>();
  }

  /**
   * Main function of scoreDP class, The method will receive two parameters one is the Golden
   * Standard Path, another is the real Path from the user
   *
   */
  public static double scoreDPpending(Path p1, Path p2){
      return scoreDP(p1, p2);
  }
 
  public static double scoreDP(Path p1, Path p2) {
    // firstly, check which one is the golden standard path
    // make sure p1 always the golden standard path
    if (!p1.getTag().equals("Gold Standard")) {
      if (!p2.getTag().equals("Gold Standard")) {
        throw new IllegalArgumentException(
                "None of the input is golden standard path, illegal input!");
      } else {
        return scoreDP(p2, p1);
      }
    }

    int l1 = p1.size();
    int l2 = p2.size();
    matrix = new double[l1 + 1][l2 + 1];
    path = new Pair[l1 + 1][l2 + 1];

    // the following is the DP algorithm
    // initialization for base cases:
    for (int i = 0; i <= l1; i++) {
      matrix[i][0] = i * -1;
    }
    for (int j = 0; j <= l2; j++) {
      matrix[0][j] = j * -1;
    }

    // the dp algorithm for String Alignment
    for (int i = 1; i <= l1; i++) {
      for (int j = 1; j <= l2; j++) {
        double match = matrix[i - 1][j - 1] + p1.get(i - 1).stepScore(p2.get(j - 1));
        double skip1 = matrix[i][j - 1] - 1;
        double skip2 = matrix[i - 1][j] - 1;
        matrix[i][j] = Math.max(match, Math.max(skip1, skip2));
      }
    }
    doBackTrack(p1, p2);
    return matrix[l1][l2];
  }

  private static void doBackTrack(Path p1, Path p2) {

    int i = p1.size();
    int j = p2.size();
//    backtrack.add(0, new Pair(i, j));
    while (i > 0 && j > 0) {   
      if(matrix[i][j] == matrix[i - 1][j - 1] + p1.get(i - 1).stepScore(p2.get(j - 1))){
        i--; j--;
//        System.out.println(p2.get(j).getStep());
        backtrack.add(0, new Step(p2.get(j)));
      }else if(matrix[i][j] == matrix[i][j - 1] - 1){
        j--;
      }else{
        i--;
      }
    }
//    for(Pair p : backtrack)
//        System.out.println(p.getSecond().getStep());
  }

  /**
   *
   * Main function for Unit test
   *
   */

  public static void main(String[] args) {
    Path p1 = new Path();
    p1.setTag("Gold Standard");
    Path p2 = new Path();
    Path p3 = new Path();
    for (int i = 0; i < 10; i++) {
      p1.add(new Step());
      p2.add(new Step());
      p3.add(new Step());
    }
    // test if p1 and p2 are different in the last Step
    // Step s = new Step();
    // s.setTool(new Tool());
    // p1.add(s);
    p1.add(new Step(new Patient(), new Prescription(new Drug(), 10.0, "ml"), new Tool("0",
            "Call Code", ""), Calendar.getInstance()));
    p2.add(new Step(new Patient(), new Prescription(new Drug(), 11.0, "ml"), new Tool("0",
            "Call Code", ""), Calendar.getInstance()));
    p3.add(new Step(new Patient(), new Prescription(new Drug(), 30.0, "ml"), new Tool("0",
            "Call Code", ""), Calendar.getInstance()));

    // ScoreDP sdp = new ScoreDP();
    double score2 = ScoreDP.scoreDP(p1, p2);
    double score3 = ScoreDP.scoreDP(p1, p3);
    System.out.println(score2);
    System.out.println(score3);
  }

  public static void setMatrix(double[][] matrix) {
    ScoreDP.matrix = matrix;
  }

  public static void setBacktrack(ArrayList<Step> backtrack) {
    ScoreDP.backtrack = backtrack;
  }

  public static double[][] getMatrix() {
    return matrix;
  }

  public ArrayList<Step> getBacktrack() {
    return backtrack;
  }
  
}
||||||| merged common ancestors
=======
package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;
import java.util.Calendar;

import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

/**
 *
 * This class, we try to use dynamic programming algorithm to implement the score generation
 * function for the Evaluator. This class, we try to use dynamic programming algorithm to implement
 * the score generation function for the Evaluator.
 *
 * @author Ryan Sun
 *
 */
public class ScoreDP {
  /**
   *
   * The matrix to store the immediate value of the DP algotithm
   *
   */
  private static double[][] matrix;

  private static Pair[][] path;

  /**
   *
   * ArrayList to store the backtrack path
   *
   */

  private static ArrayList<Step> backtrack;

  // private static double score;

  /**
   * constructor of the scoreDP class
   *
   */

  public ScoreDP() {
    backtrack = new ArrayList<Step>();
  }

  /**
   * Main function of scoreDP class, The method will receive two parameters one is the Golden
   * Standard Path, another is the real Path from the user
   *
   */
  public static double scoreDPpending(Path p1, Path p2) {
    return scoreDP(p1, p2);
  }

  public static double scoreDP(Path p1, Path p2) {
    // firstly, check which one is the golden standard path
    // make sure p1 always the golden standard path
    if (!p1.getTag().equals("Gold Standard")) {
      if (!p2.getTag().equals("Gold Standard")) {
        throw new IllegalArgumentException(
                "None of the input is golden standard path, illegal input!");
      } else {
        return scoreDP(p2, p1);
      }
    }

    int l1 = p1.size();
    int l2 = p2.size();
    matrix = new double[l1 + 1][l2 + 1];
    path = new Pair[l1 + 1][l2 + 1];

    // the following is the DP algorithm
    // initialization for base cases:
    for (int i = 0; i <= l1; i++) {
      matrix[i][0] = i * -1;
    }
    for (int j = 0; j <= l2; j++) {
      matrix[0][j] = j * -1;
    }

    // the dp algorithm for String Alignment
    for (int i = 1; i <= l1; i++) {
      for (int j = 1; j <= l2; j++) {
        double match = matrix[i - 1][j - 1] + p1.get(i - 1).stepScore(p2.get(j - 1));
        double skip1 = matrix[i][j - 1] - 1;
        double skip2 = matrix[i - 1][j] - 1;
        matrix[i][j] = Math.max(match, Math.max(skip1, skip2));
      }
    }
    doBackTrack(p1, p2);
    return matrix[l1][l2];
  }

  private static void doBackTrack(Path p1, Path p2) {
    int i = p1.size();
    int j = p2.size();
    // backtrack.add(0, new Pair(i, j));
    while (i > 0 && j > 0) {
      if (matrix[i][j] == matrix[i - 1][j - 1] + p1.get(i - 1).stepScore(p2.get(j - 1))) {
        i--;
        j--;
        // System.out.println(p2.get(j).getStep());
        backtrack.add(0, new Step(p2.get(j)));
      } else if (matrix[i][j] == matrix[i][j - 1] - 1) {
        j--;
      } else {
        i--;
      }
    }
    // for(Pair p : backtrack)
    // System.out.println(p.getSecond().getStep());
  }

  /**
   *
   * Main function for Unit test
   *
   */

  public static void main(String[] args) {
    Path p1 = new Path();
    p1.setTag("Gold Standard");
    Path p2 = new Path();
    Path p3 = new Path();
    for (int i = 0; i < 10; i++) {
      p1.add(new Step());
      p2.add(new Step());
      p3.add(new Step());
    }
    // test if p1 and p2 are different in the last Step
    // Step s = new Step();
    // s.setTool(new Tool());
    // p1.add(s);
    p1.add(new Step(new Patient(), new Prescription(new Drug(), 10.0, "ml"), new Tool("0",
            "Call Code", ""), Calendar.getInstance()));
    p2.add(new Step(new Patient(), new Prescription(new Drug(), 11.0, "ml"), new Tool("0",
            "Call Code", ""), Calendar.getInstance()));
    p3.add(new Step(new Patient(), new Prescription(new Drug(), 30.0, "ml"), new Tool("0",
            "Call Code", ""), Calendar.getInstance()));

    // ScoreDP sdp = new ScoreDP();
    double score2 = ScoreDP.scoreDP(p1, p2);
    double score3 = ScoreDP.scoreDP(p1, p3);
    System.out.println(score2);
    System.out.println(score3);
  }

  public static void setMatrix(double[][] matrix) {
    ScoreDP.matrix = matrix;
  }

  public static void setBacktrack(ArrayList<Step> backtrack) {
    ScoreDP.backtrack = backtrack;
  }

  public static double[][] getMatrix() {
    return matrix;
  }

  public ArrayList<Step> getBacktrack() {
    return backtrack;
  }

}
>>>>>>> ryan_Evaluator
