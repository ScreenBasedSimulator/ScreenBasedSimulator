package edu.cmu.lti.bic.sbs.ui;

//import java.time.LocalTime;
import java.awt.EventQueue;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class UserInterface {
	Engine decisionEngine = null;
	MainWindow window = null;
	private Gson gson = new Gson();

	public UserInterface(Engine in)
			throws UserInterfaceInitializationException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainWindow();

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

//	public void setTime(LocalTime time) {
//		assert(time != null);
//		window.setTime(time.getHour(), time.getMinute(), time.getSecond());
//	}

	public void setPatientInfo(Patient patient) {
		assert(patient != null);
		window.setPatient(patient.getBasic(), patient.getDescription());
	}

	public void addDrug(Drug drug) {
		assert(drug != null);
		window.addDrug(drug.getId(), drug.getName());
	}

	public void addTool() {

	}

	public void updateReport() {

	}

	public void updateMonitor() {
		
	}

	public void addPathography(String feedback) {
		
	}

}
