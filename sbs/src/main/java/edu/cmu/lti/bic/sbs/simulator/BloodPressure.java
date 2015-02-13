package edu.cmu.lti.bic.sbs.simulator;

public class BloodPressure implements MedicalParameter {
	Double systolicBloodPressure, diastolicBloodPressure;

	public BloodPressure(Double systolicBloodPressure,
			Double diastolicBloodPressure) {
		super();
		this.systolicBloodPressure = 90.0;
		this.diastolicBloodPressure = 60.0;
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
