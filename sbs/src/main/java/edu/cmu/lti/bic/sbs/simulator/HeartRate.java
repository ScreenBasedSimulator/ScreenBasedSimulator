package edu.cmu.lti.bic.sbs.simulator;

public class HeartRate implements MedicalParameter {
	Double hrNum;

	public HeartRate(Double hrNum) {
		super();
		this.hrNum = hrNum;
	}

	public Double getHrNum() {
		return hrNum;
	}

	public HeartRate setHrNum(Double hrNum) {
		this.hrNum = hrNum;
		return this;
	}
}
