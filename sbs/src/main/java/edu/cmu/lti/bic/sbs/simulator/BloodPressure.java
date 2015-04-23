package edu.cmu.lti.bic.sbs.simulator;

public class BloodPressure implements MedicalParameter {

	Double systolicBloodPressure, systolicBloodPressureLowerBound,
			systolicBloodPressureUpperBound;
	
	Double diastolicBloodPressure, diastolicBloodPressureLowerBound,
			diastolicBloodPressureUpperBound;

	// constructor function
	public BloodPressure(Double systolicBloodPressure,
			Double systolicBloodPressureLowerBound,
			Double systolicBloodPressureUpperBound,
			Double diastolicBloodPressure,
			Double diastolicBloodPressureLowerBound,
			Double diastolicBloodPressureUpperBound) {
		super();

		this.systolicBloodPressure = systolicBloodPressure;
		this.systolicBloodPressureLowerBound = systolicBloodPressureLowerBound;
		this.systolicBloodPressureUpperBound = systolicBloodPressureUpperBound;
		this.diastolicBloodPressure = diastolicBloodPressure;
		this.diastolicBloodPressureLowerBound = diastolicBloodPressureLowerBound;
		this.diastolicBloodPressureUpperBound = diastolicBloodPressureUpperBound;
		validateParameter();
	}

	/*
	 * Check if rate is in the range of lower and upper bound must be called
	 * after changing rate data
	 */
	private void validateParameter() {
		if (systolicBloodPressure > systolicBloodPressureUpperBound) {
			systolicBloodPressure = systolicBloodPressureUpperBound;
		} else if (systolicBloodPressure < systolicBloodPressureLowerBound) {
			systolicBloodPressure = systolicBloodPressureLowerBound;
		}
		
		if (diastolicBloodPressure > diastolicBloodPressureUpperBound) {
			diastolicBloodPressure = diastolicBloodPressureUpperBound;
		} else if (diastolicBloodPressure < diastolicBloodPressureLowerBound) {
			diastolicBloodPressure = diastolicBloodPressureLowerBound;
		}
	}

	public Double getSystolicBloodPressure() {
		return systolicBloodPressure;
	}

	public Double getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}

	public void setSystolicBloodPressure(Double bp) {
		this.systolicBloodPressure = bp;
		validateParameter();
	}

	public void setDiastolicBloodPressure(Double bp) {
		this.diastolicBloodPressure = bp;
		validateParameter();
	}
}
