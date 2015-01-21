package edu.cmu.lti.bic.sbs.ui.monitor;

import javax.swing.*;

/**
 * 
 * @author Guan Wang
 *
 */
public class BloodPressurePanel extends JPanel {
	private JLabel bloodPressureLabel = null;
	private final static int width = 17;
	private final static int height = 3;

	public BloodPressurePanel() {
		bloodPressureLabel = new JLabel("BP: ?/? mmHg");
		this.add(bloodPressureLabel);
	}

	public void setBloodPressure(int upperBloodPressureData,
			int lowerBloodPressureData) {
		bloodPressureLabel.setText("BP: " + upperBloodPressureData + "\\"
				+ lowerBloodPressureData + " mmHg");
	}

}
