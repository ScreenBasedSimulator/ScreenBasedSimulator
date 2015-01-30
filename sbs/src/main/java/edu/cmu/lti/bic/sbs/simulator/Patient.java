package edu.cmu.lti.bic.sbs.simulator;

enum Status{
	great, good, not_good, bad, dying
	
}

public class Patient {
	
	BloodPressure bp;
	HeartRate hr;
	OxygenLevel ol;
	RespirationRate rr;
	
	GraphicDisplay graDisplay;
	
	Status status;
	
	public Patient(BloodPressure bp, HeartRate hr, OxygenLevel ol,
			RespirationRate rr) {
		super();
		this.bp = bp;
		this.hr = hr;
		this.ol = ol;
		this.rr = rr;
	}

	public BloodPressure getBp() {
		return bp;
	}

	public void setBp(BloodPressure bp) {
		this.bp = bp;
	}

	public HeartRate getHr() {
		return hr;
	}

	public void setHr(HeartRate hr) {
		this.hr = hr;
	}

	public OxygenLevel getOl() {
		return ol;
	}

	public void setOl(OxygenLevel ol) {
		this.ol = ol;
	}

	public RespirationRate getRr() {
		return rr;
	}

	public void setRr(RespirationRate rr) {
		this.rr = rr;
	}
}
