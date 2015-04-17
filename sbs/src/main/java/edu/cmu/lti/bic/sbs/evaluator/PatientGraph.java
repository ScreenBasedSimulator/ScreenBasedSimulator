package edu.cmu.lti.bic.sbs.evaluator;

import java.util.ArrayList;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import edu.cmu.lti.bic.sbs.gson.Patient;

public class PatientGraph extends JFrame {
  
  ArrayList<Patient> patientHistory;
  
  public PatientGraph(Path p){
    patientHistory = new ArrayList<Patient>();
    for (Step s : p){
      patientHistory.add(s.getPatient());
    }
    JFreeChart rr = drawRR();
    JFreeChart bp = drawBP();
    JFreeChart ol = drawOL();
    JFreeChart hr = drawHR();
    
  }
  public JFreeChart drawRR(){
    return null;
  }
  public JFreeChart drawBP(){
    return null;
  }
  public JFreeChart drawOL(){
    return null;
  }
  public JFreeChart drawHR(){
    return null;
  }
  
  
}
