package edu.cmu.lti.bic.sbs.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrugPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8054193195906106046L;
	String[] drugName = { "Durg1", "Durg2", "Durg3", "Durg4" };
	String[] unit = { "ml", "mg" };

	/**
	 * Create the application.
	 */
	public DrugPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JComboBox<String> drugBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
		drugBox.setBounds(100, 30, 110, 50);
		drugBox.setBorder(BorderFactory.createTitledBorder("Drug"));

		JLabel dosageLabel = new JLabel("Choose dosage:");
		dosageLabel.setBounds(110, 70, 200, 50);
		this.add(dosageLabel);

		SpinnerModel doseModel = new SpinnerNumberModel(9.9, 1, 15, 0.1);     
		JSpinner doseSpinner = new JSpinner(doseModel);
		doseSpinner.setBounds(110, 110, 75, 20);
		this.add(doseSpinner);

		JButton EntryButton = new JButton("INJECT");
		EntryButton.setBounds(190, 140, 65, 30);
		this.add(EntryButton);

		JComboBox<String> unitBox = new JComboBox<String>(new DefaultComboBoxModel<String>());
		unitBox.setBounds(185, 100, 70, 43);
		this.add(unitBox);
	}

}
