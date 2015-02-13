package edu.cmu.lti.bic.sbs.ui;

import java.util.HashMap;

import javax.swing.JFrame;

import edu.cmu.lti.bic.sbs.gson.Drug;

public class DrugWindow {

	JFrame frame;
	UserInterface ui;
	/**
	 *  Create the application.
	 * @param ui user interface
	 */
	public DrugWindow(UserInterface ui) {
	  this.ui=ui;
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
		HashMap<String,Drug> drugMap=ui.getDrugMap();
		//drugPanel.addDrug(name, id)
		for(Drug drug:drugMap.values()){
		  drugPanel.addDrug(drug.getName(), drug.getId());
		}
	
	}
	

}
