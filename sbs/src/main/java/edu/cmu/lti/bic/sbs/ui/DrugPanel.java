package edu.cmu.lti.bic.sbs.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.WindowConstants;

public class DrugPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8054193195906106046L;
	String[] unit = { "ml", "mg" };
	HashMap<String, String> drugInStore = new HashMap<String, String>();// name,
																																			// id
	UserInterface ui;

	DefaultComboBoxModel<String> drugModel=new DefaultComboBoxModel<String>();
	DefaultComboBoxModel<String> unitModel=new DefaultComboBoxModel<String>();
	//DrugWindow drugWindow;
	public JFrame jFrame=null;

	/**
	 * initialize the drug panel and pass the ui parameter in.
	 * 
	 * @param ui
	 *          user interface
	 */

	void addDrug(String name, String id) {
		if (!drugInStore.containsKey(name)) {
			drugInStore.put(name, id);
			drugModel.addElement(name);
		} else
			System.out.println("This drug has already existed!");
	}

	/**
	 * initialize the drug panel and pass the ui parameter in.
	 * 
	 * @param ui
	 */
	public DrugPanel(UserInterface ui) {
		initialize();

		this.ui = ui;
		// this.drugWindow=drugWindow;

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		JComboBox<String> drugBox = new JComboBox<String>(drugModel);

		drugBox.setBounds(100, 30, 110, 50);
		drugBox.setBorder(BorderFactory.createTitledBorder("Drug"));
		this.add(drugBox);

		JLabel dosageLabel = new JLabel("Choose dosage:");
		dosageLabel.setBounds(110, 70, 200, 50);
		this.add(dosageLabel);

		final SpinnerModel doseModel = new SpinnerNumberModel(9.9, 1, 15, 0.1);
		JSpinner doseSpinner = new JSpinner(doseModel);
		doseSpinner.setBounds(110, 110, 75, 20);
		this.add(doseSpinner);

		unitModel.addElement("mL"); // test!!!
		JComboBox<String> unitBox = new JComboBox<String>(unitModel);
		unitBox.setBounds(185, 100, 70, 43);
		this.add(unitBox);

		JButton EntryButton = new JButton("INJECT");
		EntryButton.setBounds(190, 140, 65, 30);
		this.add(EntryButton);
		EntryButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// drugWindow.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// ui.useDrug(String id, Double dose, String unit);
				String name = (String) drugBox.getSelectedItem();
				String id = drugInStore.get(name);
				double dose = (double) doseModel.getValue();
				String drugUnit = (String) unitBox.getSelectedItem();
				System.out.println("iD:" + id);

				ui.useDrug(id, dose, drugUnit);
				
			}
		});
	}
}
