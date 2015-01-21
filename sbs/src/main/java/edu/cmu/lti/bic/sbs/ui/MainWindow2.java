package edu.cmu.lti.bic.sbs.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JFrame;

public class MainWindow2 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow2 window = new MainWindow2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(340, 10, 100, 97);
		frame.getContentPane().add(panel);
		
		Label label_2 = new Label("Monitor");
		label_2.setForeground(Color.WHITE);
		label_2.setBackground(Color.BLACK);
		panel.add(label_2);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(236, 10, 90, 28);
		frame.getContentPane().add(panel_1);
		
		Label label = new Label("12:00:00");
		panel_1.add(label);
		label.setFont(new Font("Dialog", Font.PLAIN, 13));
		label.setBackground(Color.BLACK);
		label.setForeground(Color.WHITE);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(334, 129, 106, 97);
		frame.getContentPane().add(panel_2);
		
		Label label_1 = new Label("Equipment");
		label_1.setForeground(Color.WHITE);
		label_1.setBackground(Color.BLACK);
		panel_2.add(label_1);
	}

}
