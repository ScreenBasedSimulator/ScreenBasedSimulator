package edu.cmu.lti.bic.sbs.simulator;

import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

//communicate with engine, just like the controller of patient
public class Simulator {
	Patient patient;

	// adding default value for four parameter
	float defaultBp;
	float defaultHr;
	float defaultOl;
	float defaultRr;

	// the initialization function for engine to involve
	public Patient initialPatient() {
		patient = new Patient();
		// pt.getBloodPressure().setBpNum(defaultBp);
		patient.getBloodPressure().setSystolicBloodPressure(90.0);
		patient.getBloodPressure().setDiastolicBloodPressure(60.0);
		patient.getHeartRate().setHrNum(80.0);
		patient.getOxygenLevel().setOlNum(99.0);
		patient.getRepiratinoRate().setRrNum(16.0);

		return patient;

	}

	public Simulator() {
		super();
		initialPatient();
	}

	public Simulator(Patient pt) {
		super();
		this.patient = pt;
	}

	//
	public Patient simPatient() {
		return patient;
	}

	public void setPt(Patient pt) {
		this.patient = pt;
	}

	public void simulateWithTool(Tool eq) {
		// set the parameters according to the equipment from engine

	}

	public void simWithDrugs(Prescription p) {
		// set the parameters according to the drug from engine

	}
}
