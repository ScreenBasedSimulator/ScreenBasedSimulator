package edu.cmu.lti.bic.sbs.gson;

import edu.cmu.lti.bic.sbs.simulator.Condition;
import edu.cmu.lti.bic.sbs.simulator.BloodPressure;
import edu.cmu.lti.bic.sbs.simulator.GraphicDisplay;
import edu.cmu.lti.bic.sbs.simulator.HeartRate;
import edu.cmu.lti.bic.sbs.simulator.OxygenLevel;
import edu.cmu.lti.bic.sbs.simulator.RespirationRate;

enum Status {
  great, good, not_good, bad, dying

}

public class Patient {
	private String basic; //eg: male, 35, white
	private String description; //eg: headache, vomit
	

	private Condition cd;

	private BloodPressure bloodPressure = new BloodPressure(90.0, 60.0);
	private HeartRate heartRate = new HeartRate(80.0);
	private OxygenLevel oxygenLevel = new OxygenLevel(0.3);
	private RespirationRate respirationRate = new RespirationRate(12.0);
	
	GraphicDisplay graDisplay;
	Status status;
	
	public Patient() {
		
	}
	public Patient(String basic, String description){
		this.basic = basic;
		this.description = description;
	}
	public Patient(Condition cd) {
		super();
		this.cd = cd;
	}
	public String getBasic() {
		return basic;
	}
	public String getDescription() {
		return description;
	}
	public BloodPressure getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(BloodPressure bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public HeartRate getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(HeartRate heartRate) {
		this.heartRate = heartRate;
	}

	public OxygenLevel getOxygenLevel() {
		return oxygenLevel;
	}

	public void setOxygenLevel(OxygenLevel oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}

	public RespirationRate getRepiratinoRate() {
		return respirationRate;
	}

	public void setRespirationRate(RespirationRate respirationRate) {
		this.respirationRate = respirationRate;
	}
}
