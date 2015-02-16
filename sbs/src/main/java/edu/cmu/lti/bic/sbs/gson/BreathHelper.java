package edu.cmu.lti.bic.sbs.gson;

public class BreathHelper extends Tool {
	
	float startTime;

	public BreathHelper(String id, String name, String description,
			float startTime) {
		super(id, name, description);
		this.startTime = startTime;
	}

	public float getStartTime() {
		return startTime;
	}

	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}
	
	
	
}
