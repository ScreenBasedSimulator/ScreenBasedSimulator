package edu.cmu.lti.bic.sbs.simulator;

public class RespirationRate implements MedicalParameter {
	private Double rrNum;
	static private Double lowerBound;
	static private Double upperBound;
	
	public RespirationRate(Double rrNum) {
		super();
		this.rrNum = rrNum;
	}

	public RespirationRate(Double rrNum, Double lowerBound, Double upperBound) {
		super();
		this.rrNum = rrNum;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		validateParameter();
	}

	/*
	 * Check if rate is in the range of lower and upper bound
	 * must be called after changing rate data
	 */
	private void validateParameter(){
		if(rrNum > upperBound){
			rrNum = upperBound;
		}
		else if(rrNum < lowerBound){
			rrNum = lowerBound;
		}
	}
	
	public Double getRrNum() {
		return rrNum;
	}

	public void setRrNum(Double rrNum) {
		this.rrNum = rrNum;
		validateParameter();
	}
	
	
}
