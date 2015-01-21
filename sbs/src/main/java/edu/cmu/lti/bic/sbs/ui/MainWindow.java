package edu.cmu.lti.bic.sbs.ui;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.gson.Equipment;

public class MainWindow {

	private JFrame frame;
	private Gson gson = new Gson();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void setVisible(boolean isVisible) {
		frame.setVisible(isVisible);
	}
	/**
	 * Create the application.
	 */
	public MainWindow() {
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
		
		ClockPanel clockPanel = new ClockPanel();
		clockPanel.setBounds(125, 32, 200, 50);
		frame.getContentPane().add(clockPanel);
		
		PatientPanel patientPanel = new PatientPanel();
		patientPanel.setBounds(101, 128, 200, 50);
		frame.getContentPane().add(patientPanel);
		
		EquipmentPanel equipmentPanel = new EquipmentPanel();
		equipmentPanel.setBounds(182, 218, 200, 50);
		frame.getContentPane().add(equipmentPanel);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(
					"src/test/resources/cli/equipmentTest.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Equipment[] equipments = null;
		equipments = gson.fromJson(fileReader, Equipment[].class);
		equipmentPanel.addEquipments(equipments);
	}
}
