package edu.cmu.lti.bic.sbs.evaluator;

import java.util.List;



/**
 * 
 * @author victorzhao, xings
 *
 */
public class Path extends ArrayList<Step> {
	String tag = ""; // tag the type of this path: goldStandard or actual

	/**
	 * The method that use to calculate the score between two paths.
	 * @param p2
	 * @return
	 */
	
	public void setTag(String tag){
	  this.tag = tag;
	}
	
	public String getTag(){
	  return this.tag;
	}
	
	public static void main(String[] args) {
		Path p = new Path();
		p.pathScore(new Path());
	}
}
