package edu.cmu.lti.bic.sbs.ui;

//import java.time.LocalTime;
import java.awt.EventQueue;
import java.util.HashMap;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class UserInterface {
	private Engine decisionEngine;
	private MainWindow window;
	private HashMap<String, Tool> toolMap;
	private UserInterface ui = this;
	
	public UserInterface(Engine decisionEngine)
			throws UserInterfaceInitializationException {
		this.decisionEngine = decisionEngine;
		this.toolMap = new HashMap<String, Tool>();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainWindow(ui);

					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void callCode(String code) {
		// decisionEngine.callCode(code);
	}

	public void connectMonitor() {
		// decisionEngine.connectMonitor();
	}
	
	public void useTool(String id) {
		Tool tool = toolMap.get(id);
		assert(tool != null);
		this.addPathography("UI: " + tool.getName() + " is used.");
		System.out.println("UI: Tool " + tool.getName() + " is used.");
		decisionEngine.useTool(tool);
	}

	public void useDrug(String id, Double dosage) {
		// decisionEngine.useDrug(drug, dosage);
	}

	// public void setTime(LocalTime time) {
	// assert(time != null);
	// window.setTime(time.getHour(), time.getMinute(), time.getSecond());
	// }

	public void setPatientInfo(Patient patient) {
		assert (patient != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.setPatient(patient.getBasic(), patient.getDescription());
			}
		});
		
	}

	public void addDrug(Drug drug) {
		assert (drug != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.addTool(drug.getId(), drug.getName());
			}
		});
	}

	public void addTool(Tool tool) {
		assert (tool != null);
		toolMap.put(tool.getId(), tool);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.addTool(tool.getId(), tool.getName());
			}
		});
	}

	public void updateReport() {

	}

	public void updateMonitor() {

	}

	public void addPathography(String feedback) {
		assert(feedback != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.addPathography(feedback);
			}
		});
	}

}
