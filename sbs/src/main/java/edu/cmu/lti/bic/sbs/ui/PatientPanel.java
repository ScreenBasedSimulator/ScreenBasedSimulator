package edu.cmu.lti.bic.sbs.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PatientPanel extends JPanel {

	JLabel basicLabel;
	JLabel descriptionLabel;

	String basic = "basic";
	String description = "description";

	public PatientPanel() {
		this.setBorder(new TitledBorder(null, "Patient", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
