package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class MonitorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3248511703631351148L;

	/**
	 * Create the application.
	 */
	public MonitorPanel() {
		// this.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		initialize();
	}

	JPanel bloodPressurePanel;
	JPanel heartRatePanel;
	JPanel oxygenLevelPanel;
	JPanel respiratoryRatePanel;

	JLabel bloodPressureLabel;
	JLabel heartRateLabel;
	JLabel oxygenLevelLabel;
	JLabel respiratoryRateLabel;
	/**
	 * initialize the monitor panel with four sub panel set up.
	 */
	private void initialize() {

		this.setBorder(new TitledBorder(null, "Monitor", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		this.setBounds(327, 61, 100, 230);
		// this.setSize(100, 230);
		// frame.getContentPane().add(monitorPanel);
		this.setLayout(null);

		bloodPressurePanel = new JPanel();
		bloodPressurePanel.setBorder(new TitledBorder(null, "BP",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bloodPressurePanel.setBounds(6, 20, 90, 50);
		this.add(bloodPressurePanel);
		// monitorPanel.add(bloodPressurePanel);

		heartRatePanel = new JPanel();
		heartRatePanel.setBorder(new TitledBorder(null, "HR",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		heartRatePanel.setBounds(6, 70, 90, 50);
		this.add(heartRatePanel);
		// monitorPanel.add(heartRatePanel);

		JPanel oxygenLevelPanel = new JPanel();
		oxygenLevelPanel.setBorder(new TitledBorder(null, "SpO2",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		oxygenLevelPanel.setBounds(6, 120, 90, 50);
		this.add(oxygenLevelPanel);
		// monitorPanel.add(oxygenLevelPanel);

		JPanel respiratoryRatePanel = new JPanel();
		respiratoryRatePanel.setBorder(new TitledBorder(null, "RR",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		respiratoryRatePanel.setBounds(6, 170, 90, 50);
		this.add(respiratoryRatePanel);
		// monitorPanel.add(respiratoryRatePanel);

		bloodPressureLabel = new JLabel("?/? mmHg");
		bloodPressurePanel.add(bloodPressureLabel);

		heartRateLabel = new JLabel("? bpm");
		heartRatePanel.add(heartRateLabel);

		oxygenLevelLabel = new JLabel("? %");
		oxygenLevelPanel.add(oxygenLevelLabel);

		respiratoryRateLabel = new JLabel("?");
		respiratoryRatePanel.add(respiratoryRateLabel);
	}


	/**
	 * setBloodPressure sets up the diastolic blood pressure and systolic blood pressure of the patient
	 * @param bloodPressureUpperBound the systolic blood pressure of current status
	 * @param bloodPressureLowerBound the diastolic blood pressure of current status
	 */

	public void setBloodPressure(Double bloodPressureUpperBound,
			Double bloodPressureLowerBound) {
		bloodPressureLabel.setText(bloodPressureUpperBound.intValue() + "/"
				+ bloodPressureLowerBound.intValue() + " mmHg");
	}
	/**
	 * set the heart rate of the patient
	 * @param heartRate the heart rate of current status
	 */
	public void setHeartRate(Double heartRate) {
		heartRateLabel.setText(heartRate.intValue() + " bpm");
	}
	/**
	 * set the oxygen level of the patient
	 * @param oxygenLevel the oxygen level of the current status
	 */
	public void setOxygenLevel(Double oxygenLevel) {
		oxygenLevelLabel.setText(Math.floor(oxygenLevel * 1000)/10 + " %");
	}
	/**
	 * set the respiratory rate of the patient
	 * @param respiratoryRate the respiratory level of the current status
	 */
	public void setRespiratoryRate(Double respiratoryRate) {
		respiratoryRate = Math.floor(respiratoryRate * 10)/10;
		respiratoryRateLabel.setText(respiratoryRate.toString());
	}
	/**
	 * clear the monitor display and reset them to ?
	 */
	public void clearMonitor() {
		
	}
}