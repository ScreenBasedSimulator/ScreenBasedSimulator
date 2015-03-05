package edu.cmu.lti.bic.sbs.evaluator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Timer Class
 * 
 * @author Victor Zhao
 *
 */
public class Timer {
<<<<<<< HEAD
	// The time has two field, one is start offset, the other 
	// is the end offset.
	private float[] offset;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	// Replacement for offset array
	private String timeStamp;
	
	public float[] getTime() {
		return offset;
	}
	
	public void setTime(float start, float end) {
		offset[0] = start;
		offset[1] = end;
	}
	
	public Timer() {
		offset = new float[2];
		Date date = new Date();
		timeStamp = dateFormat.format(date);
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
=======
  // The time has two field, one is start offset, the other
  // is the end offset.
  private float[] offset;

  private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

  // Replacement for offset array
  private String timeStamp;

  public float[] getTime() {
    return offset;
  }

  public void setTime(float start, float end) {
    offset[0] = start;
    offset[1] = end;
  }

  public Timer() {
    offset = new float[2];
    Date date = new Date();
    timeStamp = dateFormat.format(date);
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
>>>>>>> guanw
}
