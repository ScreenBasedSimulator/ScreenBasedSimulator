package edu.cmu.lti.bic.sbs.ui.monitor;
import javax.swing.*;
/**
 * 
 * @author Guan Wang
 *
 */
public class OxygenLevelPanel extends JPanel{
	private JLabel oxygenLevelLabel = null;
	private final static int width = 17;
	private final static int height = 3;

	public OxygenLevelPanel() {
		oxygenLevelLabel = new JLabel("SpO2: ? %");
		this.add(oxygenLevelLabel);
	}

	public void setOxygenLevel(int oxygenLevelData) {
		oxygenLevelLabel.setText("SpO2: " + oxygenLevelData + " %");
	}
}
