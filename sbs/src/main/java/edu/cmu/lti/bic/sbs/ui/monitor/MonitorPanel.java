package edu.cmu.lti.bic.sbs.ui.monitor;

import javax.swing.*;

/**
 * 
 * @author Guan Wang
 *
 */
public class MonitorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8888378134486405634L;
	BloodPressurePanel bloodPressurePanel = null;
	HeartRatePanel heartRatePanel = null;
	OxygenLevelPanel oxygenLevelPanel = null;
	RespiratoryRatePanel respiratoryRatePanel = null;

	public MonitorPanel() {
		bloodPressurePanel = new BloodPressurePanel();
		heartRatePanel = new HeartRatePanel();
		oxygenLevelPanel = new OxygenLevelPanel();
		respiratoryRatePanel = new RespiratoryRatePanel();
		this.add(bloodPressurePanel);
		this.add(heartRatePanel);
		this.add(oxygenLevelPanel);
		this.add(respiratoryRatePanel);
	}

	public void setBloodPressure(int upperBloodPressureData,
			int lowerBloodPressureData) {
		bloodPressurePanel.setBloodPressure(upperBloodPressureData, lowerBloodPressureData);
	}

	public void setHeartRate(int heartRateData) {
		heartRatePanel.setBloodPressure(heartRateData);
	}

	public void setOxygenLevel(int oxygenLevelData) {
		oxygenLevelPanel.setOxygenLevel(oxygenLevelData);
	}

	public void setRespiratoryRate(int respiratoryRate) {
		respiratoryRatePanel.setRespiratoryRate(respiratoryRate);
	}

}
