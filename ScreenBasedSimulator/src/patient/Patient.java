package patient;


enum Status{
	great, good, not_good, bad, dying
	
}

public class Patient {
	
	BloodPressure bp;
	HeartRate hr;
	OxygenLevel ol;
	RepositoryRate rr;
	
	GraphicDisplay graDisplay;
	
	Status status;
	
	public Patient(BloodPressure bp, HeartRate hr, OxygenLevel ol,
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
	

	public void updateWithEquipments(Equipment eq){
		//set four parameters
	}
	
	
	public void updateWithDrugs(Drug drug, float dose ){
		//set four parameters
	}
	
	
	
}
