package edu.cmu.lti.bic.sbs.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Panel;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NursePanel extends JPanel{

	private JFrame frame;
	private UserInterface ui;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public NursePanel(UserInterface ui) {
		initialize(ui);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UserInterface ui) {
		this.ui=ui;
		//frame = new JFrame();
		//frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(null);
		this.setBorder(new TitledBorder(null, "Nurse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Panel panel = new Panel();
		//this.setBounds(25, 47, 132, 114);
		//frame.getContentPane().add(panel);
		this.setLayout(null);
		
		JButton btnNewButton = new JButton("Connect Monitor");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ui.connectMonitor();
			}
		});
		btnNewButton.setBounds(6, 12, 137, 29);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Code Blue\n");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ui.callCode("Code Blue!");
			}
		});
		btnNewButton_1.setBounds(6, 46, 137, 29);
		this.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Inject");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("injection!");
			}
		});
		btnNewButton_2.setBounds(6, 79, 137, 29);
		this.add(btnNewButton_2);
		this.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnNewButton, btnNewButton_1, btnNewButton_2}));
	}
}
