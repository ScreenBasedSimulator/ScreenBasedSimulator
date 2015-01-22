package edu.cmu.lti.bic.sbs.ui;

import java.time.LocalTime;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class UserInterface {
	Engine decisionEngine = null;
	MainWindow myWindow = null;
	private JFrame frame;
	private Gson gson = new Gson();

	public UserInterface(Engine in)
			throws UserInterfaceInitializationException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();

					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void callCode(String code) {
		decisionEngine.callCode(code);
	}

	public void connectMonitor() {
		decisionEngine.connectMonitor();
	}


	public void useTool(Tool tool) {
		decisionEngine.useTool(tool);
	}

	public void useDrug(Drug drug, Double dosage) {
		decisionEngine.useDrug(drug, dosage);
	}

	public void setTime(LocalTime time) {
	}

	public void setPatientInfo(Patient patient) {
	}

	public void addDrug(Drug drug) {
		
	}

	public void addTool() {

	}

	public void updateReport() {

	}

	public void updateMonitor() {
		
	}

	public void addPathography() {
	}

}
