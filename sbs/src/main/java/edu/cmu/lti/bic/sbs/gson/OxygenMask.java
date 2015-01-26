package edu.cmu.lti.bic.sbs.gson;


public class OxygenMask extends Tool {
	float oxygenValue;

	
	//constructor function
	public OxygenMask(String id, String name, String description,
			float oxygenValue) {
		super(id, name, description);
		this.oxygenValue = oxygenValue;
	}

	public float getOxygenValue() {
		return oxygenValue;
	}

	public void setOxygenValue(float oxygenValue) {
		this.oxygenValue = oxygenValue;
	}
	
	
	
}
