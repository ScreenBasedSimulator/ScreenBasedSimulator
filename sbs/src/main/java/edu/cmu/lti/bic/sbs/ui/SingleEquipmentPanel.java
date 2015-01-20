package edu.cmu.lti.bic.sbs.ui;

import com.googlecode.lanterna.gui.component.*;
import com.googlecode.lanterna.terminal.TerminalSize;

public class SingleEquipmentPanel extends Panel {
	String id = null;
	Label nameLabel = null;
	DescriptionTextBox descriptionTextArea = null;

	public SingleEquipmentPanel(String id, String name, String description) {
		nameLabel = new Label(name);
		descriptionTextArea = new DescriptionTextBox(description);
		this.addComponent(nameLabel);
		this.addComponent(descriptionTextArea);
		descriptionTextArea.setPreferredSize(new TerminalSize(20, 3));
	}

}
