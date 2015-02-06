package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JFrame;

public class DrugWindow {

	JFrame frame;
	private DrugPanel drugPanel;
	private UserInterface ui;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public DrugWindow(UserInterface ui) {
		this.ui = ui;
		initialize(new DrugPanel(ui));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(DrugPanel drugPanel) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 70);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.drugPanel=drugPanel;
		drugPanel.setBounds(200,200 , 100, 50);
		frame.getContentPane().add(drugPanel);
		
		
	}

}
