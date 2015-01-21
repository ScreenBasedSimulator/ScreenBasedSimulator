package edu.cmu.lti.bic.sbs.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.gson.Equipment;

public class UserInterface {
	DecisionEngine decisionEngine=null;
	MainWindow myWindow = null;
	private JFrame frame;
	private Gson gson = new Gson();
	
	public UserInterface (DecisionEngine in) throws UserInterfaceInitializationException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();

					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void callCode(String code){
		decisionEngine.callCode(code);
	}
	public void connectMonitor(){
		decisionEngine.connectMonitor();
	}
	public void disconnectMonitor(){
		if(decisionEngine.isMonitorConnected()){
			decisionEngine.disconnectMonitor();
		}
	}
	public void useEquipment(Equipment equipment){
		decisionEngine.useEquipment(equipment);
	}
	public void useDrug(Drug drug,double dosage){
		decisionEngine.useDrug(drug,dosage);
	}
	
}
