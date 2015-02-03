package edu.cmu.lti.bic.sbs.ui;

//import java.time.LocalTime;
import java.awt.EventQueue;
import java.util.Calendar;
import java.util.HashMap;

import edu.cmu.lti.bic.sbs.engine.Engine;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

public class UserInterface {
	private Engine decisionEngine;
	private MainWindow window;
	private HashMap<String, Tool> toolMap;
	private HashMap<String, Drug> drugMap;
	private UserInterface ui = this;

	// private DrugWindow drugWindow;
	//
	public UserInterface(Engine decisionEngine)
			throws UserInterfaceInitializationException {
		this.decisionEngine = decisionEngine;
		this.toolMap = new HashMap<String, Tool>();
		this.drugMap = new HashMap<String, Drug>();
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
		// drugPanel=new DrugPanel(this);
	}

	public void callCode(String code) {
		// decisionEngine.callCode(code);
		ui.addPathography("Code Blue!");
	}

	public void connectMonitor() {
		decisionEngine.connectMonitor();
		ui.addPathography("Monitor connected!");
	}

	public void useTool(String id) {
		Tool tool = toolMap.get(id);
		assert (tool != null);
		this.addPathography("UI: " + tool.getName() + " is used.");
		System.out.println("UI: Tool " + tool.getName() + " is used.");
		decisionEngine.useTool(tool);
	}

	public void useDrug(String id, Double dose, String unit) {
		assert (id != null);
		Drug drug = drugMap.get(id);
		assert (drug != null);
		Prescription prescription = new Prescription(drug, dose, unit);
		decisionEngine.useDrug(prescription);
		// System.out.println("use the drug");
		ui.addPathography("used a drug!");

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

	public void updateMonitor(Patient p) {
		assert (p != null);
		assert (p.getBloodPressure() != null);
		assert (p.getHeartRate() != null);
		assert (p.getOxygenLevel() != null);
		assert (p.getRepiratinoRate() != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.setMonitor(p.getBloodPressure()
						.getDiastolicBloodPressure(), p.getBloodPressure()
						.getSystolicBloodPressure(), p.getHeartRate()
						.getHrNum(), p.getOxygenLevel().getOlNum(), p
						.getRepiratinoRate().getRrNum());
			}
		});
	}

	public void updateTime(Calendar time) {
		assert (time != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.updateClock(time.get(Calendar.HOUR_OF_DAY),
						time.get(Calendar.MINUTE), time.get(Calendar.SECOND));
			}
		});
	}

	public void addPathography(String feedback) {
		assert (feedback != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.addPathography(feedback);
			}
		});
	}

}
