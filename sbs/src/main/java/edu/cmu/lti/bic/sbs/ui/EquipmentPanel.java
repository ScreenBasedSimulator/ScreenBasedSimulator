package edu.cmu.lti.bic.sbs.ui;

import java.util.List;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.component.*;
import com.googlecode.lanterna.input.Key.Kind;

import edu.cmu.lti.bic.sbs.gson.Equipment;

/**
 * 
 * @author Xiao Han
 *
 */
public class EquipmentPanel extends Panel {

	List<SingleEquipmentPanel> panelList = null;

	public EquipmentPanel() {
		this.addComponent(new Label("initializing..."));
	}

	public void addEquipments(Equipment[] equipmentList) {
		this.removeAllComponents();
		for (Equipment equipment : equipmentList) {
			SingleEquipmentPanel newPanel = new SingleEquipmentPanel(
					equipment.getId(), equipment.getName(),
					equipment.getDescription());

			newPanel.addShortcut(Kind.Enter, new Action() {
				@Override
				public void doAction() {
					System.out.println("Equipment Selected");
				}
			});
			this.addComponent(newPanel);
		}
	}

	public void addEquipments(List<Equipment> equipmentList) {
		this.addEquipments((Equipment[]) equipmentList.toArray());
	}
}
