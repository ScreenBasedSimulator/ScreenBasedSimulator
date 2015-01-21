package edu.cmu.lti.bic.sbs.ui;

import java.time.LocalTime;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.gson.Equipment;
import edu.cmu.lti.bic.sbs.gson.Patient;

public class UserInterface {
	DecisionEngine decisionEngine = null;
	MainWindow myWindow = null;
	private JFrame frame;
	private Gson gson = new Gson();

	public UserInterface(DecisionEngine in)
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

	public void disconnectMonitor() {
		if (decisionEngine.isMonitorConnected()) {
			decisionEngine.disconnectMonitor();
		}
	}

	public void useEquipment(Equipment equipment) {
		decisionEngine.useEquipment(equipment);
	}

	public void useDrug(Drug drug, double dosage) {
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
