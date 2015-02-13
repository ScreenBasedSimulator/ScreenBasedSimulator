package edu.cmu.lti.bic.sbs.simulator;

public class RespirationRate implements MedicalParameter {
	Double rrNum;

	public RespirationRate(Double rrNum) {
		super();
		this.rrNum = rrNum;
	}

	public Double getRrNum() {
		return rrNum;
	}

	public void setRrNum(Double rrNum) {
		this.rrNum = rrNum;
	}
	
	
}
