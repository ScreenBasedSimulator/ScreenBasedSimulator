package edu.cmu.lti.bic.sbs.ui.monitor;

import javax.swing.*;

/**
 * 
 * @author Guan Wang
 *
 */
public class HeartRatePanel extends JPanel {
	private JLabel heartRateLabel = null;
	private final static int width = 17;
	private final static int height = 3;

	public HeartRatePanel() {
		heartRateLabel = new JLabel("HR: ? bpm");
		this.add(heartRateLabel);
	}

	public void setBloodPressure(int heartRateData) {
		heartRateLabel.setText("HR: " + heartRateData + " bpm");
	}

}
