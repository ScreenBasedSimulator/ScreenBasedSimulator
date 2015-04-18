package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JFrame;

public class MainWindow {

	private JFrame frame;
	private ClockPanel clockPanel;
	private PatientPanel patientPanel;
	private ToolPanel toolPanel;
	private MonitorPanel monitorPanel;
	private UserInterface ui;
	private NursePanel nursePanel; 
	private PathographyPanel pathographyPanel;
	
	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible);
	}


	/**
	 * Create the application, pass in ui parameter and initialize the main window.
	 * @param ui user interface
	 */
	public MainWindow(UserInterface ui) {
		this.ui = ui;
		initialize();
	}

	/**
	 * Initialize the contents of the main window and sets up 
	 * the clock, patient, tool, nurse and pathography panel.
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
		nursePanel.setBounds(264, 10, 158, 116);
		frame.getContentPane().add(nursePanel);
		
		pathographyPanel = new PathographyPanel();
		pathographyPanel.setBounds(264, 130, 158, 110);
		frame.getContentPane().add(pathographyPanel);
	}
	
	/**
	 * setTime enables clock panel to set the current time
	 * @param h represents the current hour
	 * @param m represents the current minute
	 * @param s represents the current second
	 */
	public void setTime(Integer h, Integer m, Integer s) {
		clockPanel.setTime(h, m, s);
	}
	
	/**
	 * setPatient enables patient panel to set the patient's medical history
	 * @param basic gives out patient's basic information
	 * @param description gives out the disease description about the patient
	 */
	public void setPatient(String basic, String description) {
		patientPanel.setBasic(basic);
		patientPanel.setDescription(description);
	}


	/**
	 * addTool makes a specific tool(equipment) available for user to use
	 * @param id the id of the tool
	 * @param name the name of the tool
	 */
	public void addTool(String id, String name) {
		toolPanel.addTool(id, name);
	}
	
	/**
	 * addPathography adds record to pathography panel.
	 * @param record the record we want to add to the panel
	 */
	public void addPathography(String record) {
		pathographyPanel.addRecord(record);
	}


	/**
	 * updateClock update the current time on clock panel
	 * @param hour the current hour
	 * @param min the current minute
	 * @param second the current second
	 */
	public void updateClock(int hour, int min, int second) {
		clockPanel.setTime(hour, min, second);
	}
	
	/**
	 * setMonitor sets up the monitor parameters for user to see
	 * @param diastolicBloodPressure the lower bound of blood pressure
	 * @param systolicBloodPressure the upper bound of blood pressure
	 * @param heartRate the heart rate of the patient
	 * @param oxygenLevel the oxygen level of the patient
	 * @param respiratoryRate the respiratory rate of the patient
	 */

	public void setMonitor(Double diastolicBloodPressure,
			Double systolicBloodPressure, Double heartRate, Double oxygenLevel,
			Double respiratoryRate) {
		// TODO Auto-generated method stub
		monitorPanel.setBloodPressure(systolicBloodPressure,
				diastolicBloodPressure);
		monitorPanel.setHeartRate(heartRate);
		monitorPanel.setOxygenLevel(oxygenLevel);
		monitorPanel.setRespiratoryRate(respiratoryRate);
	}
	
	public void closeDrugWindow() {
		nursePanel.closeDrugWindow();
	}
}