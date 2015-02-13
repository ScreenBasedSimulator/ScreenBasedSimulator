package edu.cmu.lti.bic.sbs.simulator;

public class HeartRate implements MedicalParameter {
	double hrNum;

	public HeartRate(double hrNum) {
		super();
		this.hrNum = hrNum;
	}

	public double getHrNum() {
		return hrNum;
	}

	public void setHrNum(double hrNum) {
		this.hrNum = hrNum;
	}	
}
