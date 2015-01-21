package edu.cmu.lti.bic.sbs.ui;

import java.util.List;

import javax.swing.*;

import edu.cmu.lti.bic.sbs.gson.Equipment;

/**
 * 
 * @author Xiao Han
 *
 */
public class EquipmentPanel extends JPanel {

	List<JLabel> labelList = null;

	public EquipmentPanel() {
		this.add(new JLabel("initializing..."));
	}

	public void addEquipments(Equipment[] equipmentList) {
		for (Equipment equipment : equipmentList) {
			JLabel newLabel = new JLabel(
					equipment.getId() +  equipment.getName() + 
					equipment.getDescription());

			this.add(newLabel);
		}
	}

	public void addEquipments(List<Equipment> equipmentList) {
		this.addEquipments((Equipment[]) equipmentList.toArray());
	}
}
