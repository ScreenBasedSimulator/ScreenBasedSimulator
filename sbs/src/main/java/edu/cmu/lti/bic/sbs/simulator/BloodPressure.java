package edu.cmu.lti.bic.sbs.simulator;

public class BloodPressure implements MedicalParameter {

	Double systolicBloodPressure, diastolicBloodPressure;

	static Double systolicBloodPressureLowerBound,
			systolicBloodPressureUpperBound, diastolicBloodPressureLowerBound,
			diastolicBloodPressureUpperBound;

	
	
	public BloodPressure(Double systolicBloodPressure,
			Double diastolicBloodPressure) {
		super();
		this.systolicBloodPressure = systolicBloodPressure;
		this.diastolicBloodPressure = diastolicBloodPressure;
	}

	// constructor function
	public BloodPressure(Double systolicBloodPressure,
			Double systolicBloodPressureLowerBound,
			Double systolicBloodPressureUpperBound,
			Double diastolicBloodPressure,
			Double diastolicBloodPressureLowerBound,
			Double diastolicBloodPressureUpperBound) {
		super();

		this.systolicBloodPressure = systolicBloodPressure;
		BloodPressure.systolicBloodPressureLowerBound = systolicBloodPressureLowerBound;
		BloodPressure.systolicBloodPressureUpperBound = systolicBloodPressureUpperBound;
		this.diastolicBloodPressure = diastolicBloodPressure;
		BloodPressure.diastolicBloodPressureLowerBound = diastolicBloodPressureLowerBound;
		BloodPressure.diastolicBloodPressureUpperBound = diastolicBloodPressureUpperBound;
		validateParameter();
	}

	/*
	 * Check if rate is in the range of lower and upper bound must be called
	 * after changing rate data
	 */
	private void validateParameter() {
		if (systolicBloodPressure > BloodPressure.systolicBloodPressureUpperBound) {
			systolicBloodPressure = BloodPressure.systolicBloodPressureUpperBound;
		} else if (systolicBloodPressure < BloodPressure.systolicBloodPressureLowerBound) {
			systolicBloodPressure = BloodPressure.systolicBloodPressureLowerBound;
		}

		if (diastolicBloodPressure > BloodPressure.diastolicBloodPressureUpperBound) {
			diastolicBloodPressure = BloodPressure.diastolicBloodPressureUpperBound;
		} else if (diastolicBloodPressure < BloodPressure.diastolicBloodPressureLowerBound) {
			diastolicBloodPressure = BloodPressure.diastolicBloodPressureLowerBound;
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
	
	@Override
	public String toString(){
		return "systolicBloodPressure: " + this.getSystolicBloodPressure() + " | diastolicBloodPressure: " + this.getDiastolicBloodPressure();
	}
}
