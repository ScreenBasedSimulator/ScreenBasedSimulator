package edu.cmu.lti.bic.sbs.ui.monitor;

import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;

/**
 * 
 * @author Guan Wang
 *
 */
public class BloodPressurePanel extends Panel {
	private Label bloodPressureLabel = null;
	private final static int width = 17;
	private final static int height = 3;

	public BloodPressurePanel() {
		super(new Border.Bevel(true), Panel.Orientation.HORISONTAL);
		this.setPreferredSize(new TerminalSize(width, height));
		bloodPressureLabel = new Label("BP: ?/? mmHg", Terminal.Color.RED);
		this.addComponent(bloodPressureLabel);
	}

	public void setBloodPressure(int upperBloodPressureData,
			int lowerBloodPressureData) {
		bloodPressureLabel.setText("BP: " + upperBloodPressureData + "\\"
				+ lowerBloodPressureData + " mmHg");
	}

}
