package edu.cmu.lti.bic.sbs.simulator;

public class OxygenLevel implements MedicalParameter {
	private Double olNum;
	static private Double lowerBound;
	static private Double upperBound;

	public OxygenLevel(Double olNum) {
		super();
		this.olNum = olNum;
	}

	public OxygenLevel(Double olNum, Double lowerBound, Double upperBound) {
		super();
		this.olNum = olNum;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		validateParameter();
	}

	/*
	 * Check if rate is in the range of lower and upper bound
	 * must be called after changing rate data
	 */
	private void validateParameter(){
		if(olNum > upperBound){
			olNum = upperBound;
		}
		else if(olNum < lowerBound){
			olNum = lowerBound;
		}
	}
	
	public Double getOlNum() {
		return olNum;
	}

	//TODO: set return type to void
	public void setOlNum(Double olNum) {
		this.olNum = olNum;
		validateParameter();
		//return this;
	}

	public Double oxygenFunc(float prevLevel, float interval) {
		return 0.0;
	}
}
