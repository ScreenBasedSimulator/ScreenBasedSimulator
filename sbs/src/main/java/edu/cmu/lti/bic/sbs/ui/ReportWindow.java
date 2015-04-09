package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ReportWindow {

	private JFrame frame;
	JLabel contentLabel;
	JLabel scoreLabel;

	/**
	 * Create the application.
	 */
	public ReportWindow() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 19, 400, 238);
		frame.getContentPane().add(scrollPane);
		
		contentLabel = new JLabel("Content");
		scrollPane.setViewportView(contentLabel);
		
		scoreLabel = new JLabel("Score:");
		scrollPane.setColumnHeaderView(scoreLabel);
	}
	
	public void setScore(String score) {
		scoreLabel.setText("Score:" + score);
	}
	
	public void setContent(String content) {
		contentLabel.setText(content);
	}
}
