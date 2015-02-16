package edu.cmu.lti.bic.sbs.gson;

public class InjectionTool extends Tool {
	String drugInfo;

	public InjectionTool(String id, String name, String description,
			String drugInfo) {
		super(id, name, description);
		this.drugInfo = drugInfo;
	}

	public String getDrugInfo() {
		return drugInfo;
	}

	public void setDrugInfo(String drugInfo) {
		this.drugInfo = drugInfo;
	}
	
	
	
}
