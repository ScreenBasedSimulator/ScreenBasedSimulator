package edu.cmu.lti.bic.sbs.evaluator;

public class Evaluator {
	private float score;
	
	public void calculateScore() {
		score++;
	} 
	
	public float getScore() {
		return score;
	}
	
	public String toString() {
		return "The score is " + score; 
	}
	
	public Evaluator() {
		score = 0;
	}
}
