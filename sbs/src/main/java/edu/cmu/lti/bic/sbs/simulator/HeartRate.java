package edu.cmu.lti.bic.sbs.simulator;

public class HeartRate implements MedicalParameter {
	float hrNum;

	public HeartRate(float hrNum) {
		super();
		this.hrNum = hrNum;
	}

	public float getHrNum() {
		return hrNum;
	}

	public void setHrNum(float hrNum) {
		this.hrNum = hrNum;
	}
	
	
}
