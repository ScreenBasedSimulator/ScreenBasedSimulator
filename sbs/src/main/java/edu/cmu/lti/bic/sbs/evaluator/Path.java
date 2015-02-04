package edu.cmu.lti.bic.sbs.evaluator;

import java.util.List;

/**
 * 
 * @author victorzhao
 *
 */
public class Path {
	List<Step> pathIns;
	
	String tag;

	/**
	 * The method that use to calculate the score between two paths.
	 * @param p2
	 * @return
	 */
	public double pathScore(Path p2) {
		// Required this and p2
		return 0.0;
	}
	
	public static void main(String[] args) {
		Path p = new Path();
		p.pathScore(new Path());
	}
}
