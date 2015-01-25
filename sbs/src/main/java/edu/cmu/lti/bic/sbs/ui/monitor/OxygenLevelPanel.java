package edu.cmu.lti.bic.sbs.ui.monitor;
import javax.swing.*;
/**
 * 
 * @author Guan Wang
 *
 */
public class OxygenLevelPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2415395982005131811L;
	private JLabel oxygenLevelLabel = null;

	public OxygenLevelPanel() {
		oxygenLevelLabel = new JLabel("SpO2: ? %");
		this.add(oxygenLevelLabel);
	}

	public void setOxygenLevel(int oxygenLevelData) {
		oxygenLevelLabel.setText("SpO2: " + oxygenLevelData + " %");
	}
}
