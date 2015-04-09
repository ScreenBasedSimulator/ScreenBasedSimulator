package edu.cmu.lti.bic.sbs.simulator;

public class OxygenLevel implements MedicalParameter {
	Double olNum;

	public OxygenLevel(Double olNum) {
		super();
		this.olNum = olNum;
	}

	public Double getOlNum() {
		return olNum;
	}

	public OxygenLevel setOlNum(Double olNum) {
		this.olNum = olNum;
		return this;
	}

	public Double oxygenFunc(float prevLevel, float interval) {
		return 0.0;
	}
}
