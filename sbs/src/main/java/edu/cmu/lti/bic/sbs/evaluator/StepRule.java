<<<<<<< HEAD
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
  double passScorePenalty = 0;
  double timePenalty = 1;
  double dosePenalty = 1;
  
  
  public StepRule(){
    
  }
  
  public StepRule(String ruleFile, Step step){
    
  }
  
  public StepRule(double maxSc, double maxSi, double minSc, double passSiP, double passScP,
          double timeP, double doseP){
    maxScore = maxSc;
    maxSimilarity = maxSi;
    minScore = minSc;
    passSimilarityPenalty = passSiP;
    passScorePenalty = passScP;
    timePenalty = timeP;
    dosePenalty = doseP;
  }
  
  public double maxScore(){
    return maxScore;
  }
  
  public double maxSimilarity(){
    return maxSimilarity;
  }
  
  public double minScore(){
    return minScore;
  }
  
  public double passSimilarityPenalty(){
    return passSimilarityPenalty;
  }
  
  public double passScorePenalty(){
    return passScorePenalty;
  }
  
  public double timePenalty(){
    return timePenalty;
  }
  
  public double dosePenalty(){
    return dosePenalty;
  }
  
}
||||||| merged common ancestors
=======
package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;

import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;


/**
 * The StepRule Class
 * 
 * @author Victor Zhao, Ryan Sun
 * 
 */


public class StepRule {
  double maxScore = 1;

  double maxSimilarity = 1;

  double minScore = 0;

  double passSimilarityPenalty = 1;

  double passScorePenalty = 0;

  double timePenalty = 1;

  double dosePenalty = 1;

  public StepRule() {

  }

  public StepRule(String ruleFile, Step step) {

  }

  public StepRule(double maxSc, double maxSi, double minSc, double passSiP, double passScP,
          double timeP, double doseP) {
    maxScore = maxSc;
    maxSimilarity = maxSi;
    minScore = minSc;
    passSimilarityPenalty = passSiP;
    passScorePenalty = passScP;
    timePenalty = timeP;
    dosePenalty = doseP;
  }

  public double maxScore() {
    return maxScore;
  }

  public double maxSimilarity() {
    return maxSimilarity;
  }

  public double minScore() {
    return minScore;
  }

  public double passSimilarityPenalty() {
    return passSimilarityPenalty;
  }

  public double passScorePenalty() {
    return passScorePenalty;
  }

  public double timePenalty() {
    return timePenalty;
  }

  public double dosePenalty() {
    return dosePenalty;
  }

}
>>>>>>> ryan_Evaluator
