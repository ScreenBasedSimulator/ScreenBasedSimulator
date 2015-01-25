package edu.cmu.lti.bic.sbs.simulator;

public class BloodPressure implements MedicalParameter {
	float bpNum;

	public BloodPressure(float bp) {
		super();
		this.bpNum = bp;
	}

	public float getBpNum() {
		return bpNum;
	}

	public void setBpNum(float bp) {
		this.bpNum = bp;
	}	
}
