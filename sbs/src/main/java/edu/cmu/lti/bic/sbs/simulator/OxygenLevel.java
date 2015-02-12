package edu.cmu.lti.bic.sbs.simulator;

public class OxygenLevel implements MedicalParameter {
	double olNum;

	public OxygenLevel(double olNum) {
		super();
		this.olNum = olNum;
	}

	public double getOlNum() {
		return olNum;
	}

	public void setOlNum(double olNum) {
		this.olNum = olNum;
	}
	
	
}
