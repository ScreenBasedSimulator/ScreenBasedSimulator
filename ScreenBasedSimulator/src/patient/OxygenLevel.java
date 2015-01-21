package patient;

public class OxygenLevel implements MedicalParameter {
	float olNum;

	public OxygenLevel(float olNum) {
		super();
		this.olNum = olNum;
	}

	public float getOlNum() {
		return olNum;
	}

	public void setOlNum(float olNum) {
		this.olNum = olNum;
	}
	
	
}
