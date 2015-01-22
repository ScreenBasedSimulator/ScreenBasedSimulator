package edu.cmu.lti.bic.sbs.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.JFrame;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.gson.Tool;

public class MainWindow {

	private JFrame frame;
	private ClockPanel clockPanel;
	private PatientPanel patientPanel;
	private ToolPanel toolPanel;
	private Gson gson = new Gson();

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

		clockPanel = new ClockPanel();
		clockPanel.setBounds(125, 32, 200, 50);
		frame.getContentPane().add(clockPanel);

		patientPanel = new PatientPanel();
		patientPanel.setBounds(101, 128, 200, 50);
		frame.getContentPane().add(patientPanel);

		toolPanel = new ToolPanel();
		toolPanel.setBounds(182, 218, 200, 50);
		frame.getContentPane().add(toolPanel);
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(
					"src/test/resources/cli/equipmentTest.json");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tool[] tools = null;
		tools = gson.fromJson(fileReader, Tool[].class);
		toolPanel.addTools(tools);
	}

	public void setTime(Integer h, Integer m, Integer s) {
		clockPanel.setTime(h, m, s);
	}
	
	public void setPatient(String basic, String description) {
		patientPanel.setBasic(basic);
		patientPanel.setDescription(description);
	}
	
	public void addDrug(String id, String name) {
		
	}
}
