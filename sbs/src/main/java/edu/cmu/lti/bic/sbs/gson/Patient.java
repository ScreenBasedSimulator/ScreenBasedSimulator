package edu.cmu.lti.bic.sbs.gson;

public class Patient {
	private String basic; //eg: male, 35, white
	private String description; //eg: headache, vomit
	
	public Patient(String basic, String description){
		this.basic = basic;
		this.description = description;
	}
	public String getBasic() {
		return basic;
	}
	public String getDescription() {
		return description;
	}
}
