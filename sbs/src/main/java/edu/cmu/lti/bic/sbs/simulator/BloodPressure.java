package edu.cmu.lti.bic.sbs.simulator;

public class BloodPressure implements MedicalParameter {
	float bpNum;

	public BloodPressure(float bp) {
		super();
		this.bpNum = bp;
	}

	public float getBp() {
		return bpNum;
	}

	public void setBp(float bp) {
		this.bpNum = bp;
	}
	
	
	
}
