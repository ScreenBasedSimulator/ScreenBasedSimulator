package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JFrame;

public class DrugWindow {

	JFrame frame;

	/**
	 *  Create the application.
	 * @param ui user interface
	 */
	public DrugWindow(UserInterface ui) {
		initialize(new DrugPanel(ui));
	}


	/**
	 * Initialize the contents of the frame.
	 * @param drugPanel
	 */
	private void initialize(DrugPanel drugPanel) {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 70);
		drugPanel.setBounds(200, 200, 100, 50);
		frame.getContentPane().add(drugPanel);
	}

}
