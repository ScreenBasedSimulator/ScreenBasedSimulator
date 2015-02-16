package edu.cmu.lti.bic.sbs.gson;


public class OxygenMask extends Tool {
	double oxygenValue;

	
	//constructor function
	public OxygenMask(String id, String name, String description,
			double oxygenValue) {
		super(id, name, description);
		this.oxygenValue = oxygenValue;
	}

	public double getOxygenValue() {
		return oxygenValue;
	}

	public void setOxygenValue(double oxygenValue) {
		this.oxygenValue = oxygenValue;
	}
	
	
	
}
