package edu.cmu.lti.bic.sbs.simulator;

public class Condition {
	BloodPressure bp;
	HeartRate  hr;
	OxygenLevel  ol;
	RepositoryRate  rr;
	
	public Condition(BloodPressure bp, HeartRate hr, OxygenLevel ol,
			RepositoryRate rr) {
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

	public RepositoryRate getRr() {
		return rr;
	}

	public void setRr(RepositoryRate rr) {
		this.rr = rr;
	}
	
	
	
}
