package edu.cmu.lti.bic.sbs.evaluator;
import java.util.ArrayList;

import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class State {

  Patient p;
  
  ArrayList<Prescription> prescriptions;
  
  ArrayList<Tool> tools;
  
  public State(){
    prescriptions = new ArrayList<Prescription>();
    tools = new ArrayList<Tool>();
    p = new Patient();
  }
  
}
