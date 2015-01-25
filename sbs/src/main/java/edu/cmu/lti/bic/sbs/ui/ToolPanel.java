package edu.cmu.lti.bic.sbs.ui;

import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import edu.cmu.lti.bic.sbs.gson.Tool;

/**
 * 
 * @author Xiao Han
 *
 */
public class ToolPanel extends JPanel {

	List<JLabel> labelList = null;

	public ToolPanel() {
		this.setBorder(new TitledBorder(null, "Tool", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.add(new JLabel("initializing..."));
	}

	public void addTools(Tool[] ToolList) {
		for (Tool tool : ToolList) {
			JLabel newLabel = new JLabel(
					tool.getId() +  tool.getName() + 
					tool.getDescription());

			this.add(newLabel);
		}
	}

	public void addEquipments(List<Tool> equipmentList) {
		this.addTools((Tool[]) equipmentList.toArray());
	}
}
