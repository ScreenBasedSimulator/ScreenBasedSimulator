package edu.cmu.lti.bic.sbs.evaluator;

/**
 * The Timer Class
 * @author Victor Zhao <xinyunzh@andrew.cmu.edu>
 *
 */
public class Timer {
	// The time has two field, one is start offset, the other 
	// is the end offset.
	private float[] offset;
	
	public float[] getTime() {
		return offset;
	}
	
	public void setTime(float start, float end) {
		offset[0] = start;
		offset[1] = end;
	}
	
	public Timer() {
		offset = new float[2];
	}
	
	public String toString() {
		return "The time period is starting from " + offset[0] + " to " + offset[1];
	}
	
	public static void main(String[] args) {
		Timer t = new Timer();
		t.setTime(0, 1);
		System.out.println(t.getTime());
		System.out.println(t.toString());
	}
}
