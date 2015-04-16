package edu.cmu.lti.bic.sbs.simulator;

public class HeartRate implements MedicalParameter {
	private Double hrNum;
	private Double lowerBound;
	private Double upperBound;	

	public HeartRate(Double hrNum, Double lowerBound, Double upperBound) {
		super();
		this.hrNum = hrNum;		
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		validateParameter();
	}

	/*
	 * Check if rate is in the range of lower and upper bound
	 * must be called after changing rate data
	 */
	private void validateParameter(){
		if(hrNum > upperBound){
			hrNum = upperBound;
		}
		else if(hrNum < lowerBound){
			hrNum = lowerBound;
		}
	}
	
	public Double getHrNum() {
		return hrNum;
	}

	//TODO: change return type
	public void setHrNum(Double hrNum) {
		this.hrNum = hrNum;
		validateParameter();
		//return this;
	}
}
