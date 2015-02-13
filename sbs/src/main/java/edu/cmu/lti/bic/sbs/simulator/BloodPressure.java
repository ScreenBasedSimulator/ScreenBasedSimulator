package edu.cmu.lti.bic.sbs.simulator;

public class BloodPressure implements MedicalParameter {
	double bpNum;

	public BloodPressure(double bp) {
		super();
		this.bpNum = bp;
	}

	public double getBpNum() {
		return bpNum;
	}

	public void setBpNum(double bp) {
		this.bpNum = bp;
	}	
}
