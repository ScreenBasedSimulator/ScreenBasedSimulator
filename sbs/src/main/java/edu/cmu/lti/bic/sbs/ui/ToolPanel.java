package edu.cmu.lti.bic.sbs.ui;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Xiao Han
 *
 */
public class ToolPanel extends JPanel {

	private static final long serialVersionUID = -8601396289086820070L;
	List<JLabel> labelList;
	UserInterface ui;

	/**
	 * the constructor initialized the tool panel and pass in the ui parameter
	 * 
	 * @param ui
	 *          user interface
	 */
	public ToolPanel(UserInterface ui) {
		this.ui = ui;
		this.setBorder(new TitledBorder(null, "Tool", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		this.setOpaque(false);
	}

	/**
	 * addTool add tool for user to choose from
	 * 
	 * @param id
	 *          the id of the tool
	 * @param name
	 *          the name of the tool
	 */

	public void addTool(String id, String name) {
		JButton newButton = new JButton();
		newButton.setAction(new ToolAction(id, name));
		this.add(newButton);
	}

	private class ToolAction extends AbstractAction {

		private static final long serialVersionUID = -5506417908572854974L;
		String id;

		public ToolAction(String id, String name) {
			putValue(NAME, name);
			putValue(SHORT_DESCRIPTION, "Use " + name);
			this.id = id;
		}

		public void actionPerformed(ActionEvent e) {
			ui.useTool(id);
		}
	}
}
