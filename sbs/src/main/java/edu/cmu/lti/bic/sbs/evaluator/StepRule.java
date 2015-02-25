package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;

import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class StepRule {
  double maxScore = 1;
  double maxSimilarity = 1;
  double minScore = 0;
  double passSimilarityPenalty = 1;
  double passScorePenaty = 0;
  double timePenalty = 1;
  double dosePenalty = 1;
  
  
  private StepRule(){
    
  }
  
  public StepRule(String ruleFile, Step step){
    
  }
}
