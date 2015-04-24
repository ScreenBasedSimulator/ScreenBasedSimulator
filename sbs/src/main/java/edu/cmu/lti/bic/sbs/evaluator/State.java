package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;

import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class State {

	private Patient patient;

	private ArrayList<Prescription> prescriptions;

	private ArrayList<Tool> tools;

	public State() {
		prescriptions = new ArrayList<Prescription>();
		tools = new ArrayList<Tool>();
		patient = new Patient();
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient p) {
		this.patient = p;
	}

	public ArrayList<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(ArrayList<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public ArrayList<Tool> getTools() {
		return tools;
	}

	public void setTools(ArrayList<Tool> tools) {
		this.tools = tools;
	}
	

}
