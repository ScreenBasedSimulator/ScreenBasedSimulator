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

  private static ArrayList<Pair> backtrack;

  // private static double score;

  /**
   * constructor of the scoreDP class
   *
   */

  public ScoreDP() {
    backtrack = new ArrayList<Pair>();
  }

  /**
   * Main function of scoreDP class, The method will receive two parameters one is the Golden
   * Standard Path, another is the real Path from the user
   *
   */

  public static double scoreDPpending(Path p1, Path p2) {
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
        double skip2 = matrix[i - 1][j];
        if (skip1 >= skip2) {
          if (match > skip1) {
            matrix[i][j] = skip1;
            path[i][j] = new Pair(i - 1, j - 1);
          } else {
            matrix[i][j] = match;
            path[i][j] = new Pair(i, j - 1);
          }
        } else {
          if (match > skip2) {
            matrix[i][j] = skip2;
            path[i][j] = new Pair(i - 1, j - 1);
          } else {
            matrix[i][j] = match;
            path[i][j] = new Pair(i - 1, j);
          }
        }
        matrix[i][j] = Math.max(match, Math.max(skip1, skip2));
      }
    }
    doBackTrack(l1, l2);
    return matrix[l1][l2];
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
        if (skip1 >= skip2) {
          if (match > skip1) {
            matrix[i][j] = skip1;
            path[i][j] = new Pair(i - 1, j - 1);
          } else {
            matrix[i][j] = match;
            path[i][j] = new Pair(i, j - 1);
          }
        } else {
          if (match > skip2) {
            matrix[i][j] = skip2;
            path[i][j] = new Pair(i - 1, j - 1);
          } else {
            matrix[i][j] = match;
            path[i][j] = new Pair(i - 1, j);
          }
        }
      }
    }
    doBackTrack(l1, l2);
    return matrix[l1][l2];
  }

  private static void doBackTrack(int l1, int l2) {
    l1--;
    l2--;
    while (l1 >= 0 && l2 >= 0) {
      backtrack.add(path[l1][l2]);
      l1 = (int) path[l1][l2].getFirst();
      l2 = (int) path[l1][l2].getSecond();
    }
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

  public static void setBacktrack(ArrayList<Pair> backtrack) {
    ScoreDP.backtrack = backtrack;
  }

  public static double[][] getMatrix() {
    return matrix;
  }

  public static ArrayList<Pair> getBacktrack() {
    return backtrack;
  }
}