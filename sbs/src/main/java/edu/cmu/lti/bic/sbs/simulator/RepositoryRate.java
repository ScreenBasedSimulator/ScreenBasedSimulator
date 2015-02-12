package edu.cmu.lti.bic.sbs.simulator;

public class RepositoryRate implements MedicalParameter {
	double rrNum;

	public RepositoryRate(double rrNum) {
		super();
		this.rrNum = rrNum;
	}

	public double getRrNum() {
		return rrNum;
	}

	public void setRrNum(double rrNum) {
		this.rrNum = rrNum;
	}
	
	
}
