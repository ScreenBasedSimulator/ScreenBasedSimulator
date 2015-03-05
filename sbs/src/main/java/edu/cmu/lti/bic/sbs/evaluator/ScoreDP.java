package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;

/**
 * 
 * This class, we try to use dynamic programming algorithm to implement the
 * score generation function for the Evaluator.
 * 
 * @author Ryan Sun
 *
 */
public class ScoreDP {

	/**
	 * The matrix for store the intermediate data
	 */
	private static double[][] matrix;

	/**
	 * The ArrayList for store the backtrack Steps
	 * 
	 * Suggestion given by Victor: These variables may be better if they are
	 * non-static.
	 */
	private static ArrayList<Step> backTrack;

	// private static double score;

	/**
	 * constructor of the scoreDP class
	 * 
	 */

	public ScoreDP() {
	}

	/**
	 * Main function of scoreDP class, The method will receive two parameters one
	 * is the Golden Standard Path, another is the real Path from the user
	 * 
	 */

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
				double match = matrix[i - 1][j - 1]
						+ p1.get(i - 1).stepScore(p2.get(j - 1));
				double skip1 = matrix[i][j - 1] - 1;
				double skip2 = matrix[i - 1][j] - 1;
				matrix[i][j] = Math.max(match, Math.max(skip1, skip2));
				// It seems that here should be several operations for
				// backtrack.
			}
		}
		return matrix[l1][l2];
	}

	/**
	 * The method that is used to return the matched steps between scored path and
	 * golden standard.
	 * 
	 * @return The concatenate step in String type. Separated by ",".
	 * @throws Exception
	 */
	public String matchedStep() throws Exception {
		if (backTrack == null || backTrack.size() == 0) {
			throw new Exception("Scoring may not been executed correctly.");
		}
		StringBuilder sb = new StringBuilder();
		for (Step s : backTrack) {
			sb.append(s.getStep() + ",");
		}
		sb.substring(0, sb.length() - 1);
		return sb.toString();
	}

	/**
	 * Main function for Unit test
	 */

	public static void main(String[] args) {
		Path p1 = new Path();
		p1.setTag("Gold Standard");
		Path p2 = new Path();
		for (int i = 0; i < 10; i++) {
			p1.add(new Step());
			p2.add(new Step());
		}
		// test if p1 and p2 are different in the last Step
		// Step s = new Step();
		// s.setTool(new Tool());
		// p1.add(s);
		p1.add(new Step());
		p2.add(new Step());

		// ScoreDP sdp = new ScoreDP();
		double score = ScoreDP.scoreDP(p1, p2);
		System.out.println(score);
	}
}
