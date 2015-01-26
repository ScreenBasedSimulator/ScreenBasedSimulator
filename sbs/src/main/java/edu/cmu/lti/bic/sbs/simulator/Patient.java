package edu.cmu.lti.bic.sbs.simulator;
import edu.cmu.lti.bic.sbs.gson.*;

enum Status{
	great, good, not_good, bad, dying
	
}

public class Patient {
	
	Condition cd;
	
	GraphicDisplay graDisplay;
	
	Status status;
	
	public Patient(BloodPressure bp, HeartRate hr, OxygenLevel ol,
			RepositoryRate rr) {
		super();
		
		cd  = new Condition(bp, hr, ol, rr);
		
		graDisplay = null;
		
		System.out.println("initialize a patient.");
	}

	public BloodPressure getBp() {
		return cd.getBp();
	}

	public void setBp(BloodPressure bp) {
		cd.setBp(bp);
	}

	public HeartRate getHr() {
		return cd.getHr();
	}

	public void setHr(HeartRate hr) {
		cd.setHr(hr);
	}

	public OxygenLevel getOl() {
		return cd.getOl();
	}

	public void setOl(OxygenLevel ol) {
		cd.setOl(ol);
	}

	public RepositoryRate getRr() {
		return cd.getRr();
	}

	public void setRr(RepositoryRate rr) {
		cd.setRr(rr);
	}

	public Condition getCd() {
		return cd;
	}

	public void setCd(Condition cd) {
		this.cd = cd;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
