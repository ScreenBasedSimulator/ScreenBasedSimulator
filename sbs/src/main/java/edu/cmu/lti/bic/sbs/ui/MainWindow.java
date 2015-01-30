package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JFrame;
import javax.swing.border.TitledBorder;

public class MainWindow {

	private JFrame frame;
	private ClockPanel clockPanel;
	private PatientPanel patientPanel;
	private ToolPanel toolPanel;
	private MonitorPanel monitorPanel;
	private UserInterface ui;
	private NursePanel nursePanel; 

	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible);
	}

	/**
	 * Create the application.
	 */
	public MainWindow(UserInterface ui) {
		this.ui = ui;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		monitorPanel = new MonitorPanel();
		monitorPanel.setBounds(10, 10, 106, 230);
		frame.getContentPane().add(monitorPanel);
		
		clockPanel = new ClockPanel();
		clockPanel.setBounds(424, 10, 120, 43);
		frame.getContentPane().add(clockPanel);

		patientPanel = new PatientPanel();
		patientPanel.setBounds(424, 55, 120, 185);
		frame.getContentPane().add(patientPanel);

		toolPanel = new ToolPanel(ui);
		toolPanel.setBounds(122, 10, 136, 230);
		frame.getContentPane().add(toolPanel);
		
		nursePanel=new NursePanel(ui);
		//nursePanel.setLocation(456, 10);
		//nursePanel.setSize(125, 116);
		nursePanel.setBounds(264, 10, 158, 116);
		frame.getContentPane().add(nursePanel);

	}

	public void setTime(Integer h, Integer m, Integer s) {
		clockPanel.setTime(h, m, s);
	}

	public void setPatient(String basic, String description) {
		patientPanel.setBasic(basic);
		patientPanel.setDescription(description);
	}

	public void setMonitor(int bloodPressureUpperBound,
			int bloodPressureLowerBound, int heartRate, int oxygenLevel,
			int respiratoryRate) {
		monitorPanel.setBloodPressure(bloodPressureUpperBound,
				bloodPressureLowerBound);
		monitorPanel.setHeartRate(heartRate);
		monitorPanel.setOxygenLevel(oxygenLevel);
		monitorPanel.setRespiratoryRate(respiratoryRate);
	}

	/*
	 * public void addDrug(String id, String name) {
	 * 
	 * }
	 */
	public void addTool(String id, String name) {
		toolPanel.addTool(id, name);
	}
}
