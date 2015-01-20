package edu.cmu.lti.bic.sbs.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.googlecode.lanterna.gui.Window;

import edu.cmu.lti.bic.sbs.ui.monitor.MonitorPanel;
import edu.cmu.lti.bic.sbs.gson.Equipment;

public class MainWindow extends Window {
	Gson gson = new Gson();

	public MainWindow(String title) throws FileNotFoundException {
		super(title);
		// TODO Auto-generated constructor stub
		EquipmentPanel equipmentPanel = new EquipmentPanel();
		FileReader fileReader = new FileReader(
				"src/test/resources/cli/equipmentTest.json");
		Equipment[] equipments = null;
		equipments = gson.fromJson(fileReader, Equipment[].class);
		equipmentPanel.addEquipments(equipments);
		this.addComponent(equipmentPanel);
		this.addComponent(new ClockPanel());
		this.addComponent(new MonitorPanel());
	}
}
