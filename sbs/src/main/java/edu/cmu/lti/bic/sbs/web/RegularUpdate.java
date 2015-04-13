package edu.cmu.lti.bic.sbs.web;

import java.util.Calendar;

import edu.cmu.lti.bic.sbs.simulator.BloodPressure;
import edu.cmu.lti.bic.sbs.simulator.HeartRate;
import edu.cmu.lti.bic.sbs.simulator.OxygenLevel;
import edu.cmu.lti.bic.sbs.simulator.RespirationRate;

public class RegularUpdate {
	private BloodPressure bloodPressure;

	private HeartRate heartRate;
	private OxygenLevel oxygenLevel;
	private RespirationRate respirationRate;
	private long time;
	private boolean isOver;
	public RegularUpdate(BloodPressure bloodPressure, HeartRate heartRate, OxygenLevel oxygenLevel,
			RespirationRate respirationRate, long time, boolean isOver) {
		this.bloodPressure = bloodPressure;
		this.heartRate = heartRate;
		this.oxygenLevel = oxygenLevel;
		this.respirationRate = respirationRate;
		this.time = time;
		this.isOver = isOver;
	}
	public RegularUpdate(BloodPressure bloodPressure, HeartRate heartRate, OxygenLevel oxygenLevel,
			RespirationRate respirationRate, int time) {
		this(bloodPressure, heartRate, oxygenLevel, respirationRate, time, false);
	}

}
