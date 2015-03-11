package edu.cmu.lti.bic.sbs.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7870609968350215718L;

	public MainJFrame() {
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon(
				"src/test/resources/images/background.jpg")));
		setLayout(new FlowLayout());
		// Just for refresh :) Not optional!
		setSize(399, 399);
		setSize(400, 400);
	}

}
