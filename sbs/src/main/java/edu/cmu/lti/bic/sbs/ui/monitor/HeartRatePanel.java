package edu.cmu.lti.bic.sbs.ui.monitor;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Guan Wang
 *
 */
public class HeartRatePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3036496866526146305L;
	private JLabel heartRateLabel = null;

	public HeartRatePanel() {
		heartRateLabel = new JLabel("HR: ? bpm");
		this.add(heartRateLabel);
	}

	public void setBloodPressure(int heartRateData) {
		heartRateLabel.setText("HR: " + heartRateData + " bpm");
	}

}
