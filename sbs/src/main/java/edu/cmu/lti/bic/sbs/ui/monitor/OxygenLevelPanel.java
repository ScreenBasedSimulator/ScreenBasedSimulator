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
public class OxygenLevelPanel extends Panel{
	private Label oxygenLevelLabel = null;
	private final static int width = 17;
	private final static int height = 3;

	public OxygenLevelPanel() {
		super(new Border.Bevel(true),
				Panel.Orientation.HORISONTAL);
		this.setPreferredSize(new TerminalSize(width, height));
		oxygenLevelLabel = new Label("SpO2: ? %", Terminal.Color.BLUE);
		this.addComponent(oxygenLevelLabel);
	}

	public void setOxygenLevel(int oxygenLevelData) {
		oxygenLevelLabel.setText("SpO2: " + oxygenLevelData + " %");
	}
}
