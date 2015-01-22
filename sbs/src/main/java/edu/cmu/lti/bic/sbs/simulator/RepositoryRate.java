package edu.cmu.lti.bic.sbs.simulator;

public class RepositoryRate implements MedicalParameter {
	float rrNum;

	public RepositoryRate(float rrNum) {
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
