package edu.cmu.lti.bic.sbs.simulator;

public class RespirationRate implements MedicalParameter {
	float rrNum;

	public RespirationRate(float rrNum) {
		super();
		this.rrNum = rrNum;
	}

	public float getRrNum() {
		return rrNum;
	}

	public void setRrNum(float rrNum) {
		this.rrNum = rrNum;
	}
	
	
}
