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
	private ReportWindow reportWindow;
	private HashMap<String, Tool> toolMap;
	private HashMap<String, Drug> drugMap;
	private UserInterface ui = this;

	/**
	 * initialize the user interface controller and connect it with decision
	 * engine
	 * 
	 * @param decisionEngine
	 *          the decision engine it's connected to
	 * @throws Exception
	 */

	public UserInterface(Engine decisionEngine) throws Exception {
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
	}

	public void callCode(String code) {
		// decisionEngine.callCode(code);
		ui.addPathography("Code Blue!");
		try {
			decisionEngine.useTool("codeblue");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Sound.play("alarm");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connectMonitor() {
		decisionEngine.connectMonitor();
		ui.addPathography("Monitor connected!");
	}

	public void useTool(String id) {
		try {
			decisionEngine.useTool(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void useDrug(String id, Double dose, String unit) {
		assert (id != null);
		try {
			decisionEngine.useDrug(id, dose, unit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ui.addPathography("used a drug!");
		window.closeDrugWindow();
	}

	public void setPatientInfo(final Patient patient) {
		assert (patient != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.setPatient(patient.getBasic(), patient.getDescription());
			}
		});

	}

	public void addDrug(final Drug drug) {
		assert (drug != null);
		drugMap.put(drug.getId(), drug);
	}
	public void addDrug(Drug[] drugMap){
	  
	  for(Drug d:drugMap){
	    addDrug(d);
	  }
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
	
	public void addTools(Tool[] tools) {
		for (Tool tool: tools) {
			addTool(tool);
		}
	}

	public void updateReport(Double score, String report) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (reportWindow == null)
						reportWindow = new ReportWindow();
					reportWindow.setScore(score.toString());
					reportWindow.setContent(report);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void updateMonitor(Patient p) {
		assert (p != null);
		assert (p.getBloodPressure() != null);
		assert (p.getHeartRate() != null);
		assert (p.getOxygenLevel() != null);
		assert (p.getRepiratinoRate() != null);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				window.setMonitor(p.getBloodPressure().getDiastolicBloodPressure(), p
						.getBloodPressure().getSystolicBloodPressure(), p.getHeartRate()
						.getHrNum(), p.getOxygenLevel().getOlNum(), p.getRepiratinoRate()
						.getRrNum());
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
	
	public HashMap<String,Drug> getDrugMap(){
	  System.out.println(drugMap);
	  return drugMap;
	}

}
