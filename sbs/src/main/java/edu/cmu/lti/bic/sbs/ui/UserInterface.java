package edu.cmu.lti.bic.sbs.ui;

import java.io.FileNotFoundException;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.GUIScreen.Position;

import edu.cmu.lti.bic.sbs.gson.Equipment;

public class UserInterface {
	DecisionEngine decisionEngine=null;
	MainWindow myWindow = null;
	GUIScreen textGUI = null;
	public UserInterface (DecisionEngine in) throws UserInterfaceInitializationException {
		decisionEngine=in;
		textGUI = TerminalFacade.createGUIScreen();
		if (textGUI == null) {
			throw new UserInterfaceInitializationException("Couldn't allocate a terminal");
		}
		try {
			myWindow = new MainWindow("Demo");
		} catch (FileNotFoundException e) {
			throw new UserInterfaceInitializationException("File not found");
		}
	}
	public void show() {
		textGUI.getScreen().startScreen();
		textGUI.showWindow(myWindow, Position.CENTER);
	}
	public void stop() {
		textGUI.getScreen().stopScreen();
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
