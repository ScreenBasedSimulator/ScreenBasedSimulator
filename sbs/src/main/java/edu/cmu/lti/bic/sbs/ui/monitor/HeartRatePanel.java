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
public class HeartRatePanel extends Panel {
	private Label heartRateLabel = null;
	private final static int width = 17;
	private final static int height = 3;

	public HeartRatePanel() {
		super(new Border.Bevel(true), Panel.Orientation.HORISONTAL);
		this.setPreferredSize(new TerminalSize(width, height));
		heartRateLabel = new Label("HR: ? bpm", Terminal.Color.YELLOW);
		this.addComponent(heartRateLabel);
	}

	public void setBloodPressure(int heartRateData) {
		heartRateLabel.setText("HR: " + heartRateData + " bpm");
	}

}
