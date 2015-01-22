package edu.cmu.lti.bic.sbs.ui;

import javax.swing.*;

public class PatientPanel extends JPanel {

	JLabel basicLabel;
	JLabel descriptionLabel;

	String basic = "basic";
	String description = "description";

	public PatientPanel() {
		basicLabel = new JLabel();
		descriptionLabel = new JLabel();
		this.add(basicLabel);
		this.add(descriptionLabel);
	}

	void setDescription(String str) {
		descriptionLabel.setText(str);
	}

	void setBasic(String str) {
		basicLabel.setText(str);
	}
}
