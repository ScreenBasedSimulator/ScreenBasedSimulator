package edu.cmu.lti.bic.sbs.simulator;

public class BloodPressure implements MedicalParameter {
	Double systolicBloodPressure, diastolicBloodPressure;

	//constructor function
	public BloodPressure(Double systolicBloodPressure,
			Double diastolicBloodPressure) {
		super();
		this.systolicBloodPressure = systolicBloodPressure;
		this.diastolicBloodPressure = diastolicBloodPressure;
	}

	public Double getSystolicBloodPressure() {
		return systolicBloodPressure;
	}

	public Double getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}

	public void setSystolicBloodPressure(Double bp) {
		this.systolicBloodPressure = bp;
	}

	public void setDiastolicBloodPressure(Double bp) {
		this.diastolicBloodPressure = bp;
	}
}
