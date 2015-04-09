package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;


import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NursePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3126359881920225699L;
	DrugWindow window;
	/**
	 * Launch the application.
	 */

	public NursePanel(UserInterface ui) {
		initialize(ui);
	}

	/**
	 * Initialize the contents of the frame and sets up three event listener.
	 */
	private void initialize(UserInterface ui) {

		this.setBorder(new TitledBorder(null, "Nurse", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		this.setLayout(null);

		final JButton connectMonitorBtn = new JButton("Connect Monitor");
		connectMonitorBtn.addMouseListener(new MouseAdapter() {
			/**
			 * mouseClicked sets up the event when the connect monitor button is
			 * clicked
			 */

			@Override
			public void mouseClicked(MouseEvent e) {
				ui.connectMonitor();
				connectMonitorBtn.setEnabled(false);
				connectMonitorBtn.setText("Monitor Connected");
			}
		});
		connectMonitorBtn.setBounds(6, 12, 137, 29);
		this.add(connectMonitorBtn);

		JButton callCodeBtn = new JButton("Code Blue");
		callCodeBtn.addMouseListener(new MouseAdapter() {
			/**
			 * mouseClicked sets up the event when the call code button is clicked
			 */

			@Override
			public void mouseClicked(MouseEvent e) {
				ui.callCode("Code Blue");
			}
		});
		callCodeBtn.setBounds(6, 46, 137, 29);

		this.add(callCodeBtn);
		JButton injectBtn = new JButton("Inject");

		injectBtn.addMouseListener(new MouseAdapter() {
			/**
			 * mouseClicked sets up the event when the inject button is clicked
			 */

			@Override
			public void mouseClicked(MouseEvent e) {
				// System.out.println("injection!");
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							window = new DrugWindow(ui);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		injectBtn.setBounds(6, 79, 137, 29);
		this.add(injectBtn);
		
		JButton resetButton = new JButton("Restart");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.restart();
			}
		});
		resetButton.setBounds(6, 120, 137, 29);
		add(resetButton);
	}
	public void closeDrugWindow() {
		window.frame.setVisible(false); //you can't see me!
		window.frame.dispose(); //Destroy the JFrame object
	}
}
