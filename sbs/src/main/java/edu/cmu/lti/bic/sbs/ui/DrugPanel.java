package edu.cmu.lti.bic.sbs.ui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class DrugPanel {

  private JFrame frame;
  String[] drugName = {"Durg1", "Durg2", "Durg3", "Durg4"};
  String[] dosage = {"1", "10", "50", "100"};
  String[] unit = {"ML", "L"};
  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          DrugPanel window = new DrugPanel();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public DrugPanel() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    frame = new JFrame();
    frame.getContentPane().setForeground(Color.WHITE);
    frame.getContentPane().setLayout(null);
        
    JComboBox drugBox = new JComboBox(new DrugModel());
    drugBox.setBounds(100, 30, 110, 50);
    drugBox.setBorder(BorderFactory.createTitledBorder("Drug"));
    frame.getContentPane().add(drugBox);
    frame.pack();
    frame.addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        System.exit(0); 
        }
    });   
    
    JLabel dosageLabel = new JLabel("Choose dosage:");
    dosageLabel.setBounds(110, 70, 200, 50);
    frame.getContentPane().add(dosageLabel);
    
    JComboBox dosageBox = new JComboBox(new DosageModel());
    dosageBox.setBounds(110, 95, 75, 50);
    frame.getContentPane().add(dosageBox);
    
    JButton EntryButton = new JButton("ENTRY");
    EntryButton.setBounds(190, 140, 65, 30);
    frame.getContentPane().add(EntryButton);
    EntryButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			ui.callCode("Code Blue!");
		}
	});
    
    
    JComboBox unitBox = new JComboBox(new UnitModel());
    unitBox.setBounds(185, 100, 70, 43);
    frame.getContentPane().add(unitBox);
    frame.setBounds(100, 100, 300, 187);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  class DrugModel extends DefaultComboBoxModel{
    DrugModel(){
    for (int i = 0; i < drugName.length; i++)
      addElement(drugName[i]);
    }
  }
  
  class DosageModel extends DefaultComboBoxModel{
    DosageModel(){
    for (int i = 0; i < dosage.length; i++)
      addElement(dosage[i]);
    }
  }
  
  class UnitModel extends DefaultComboBoxModel{
    UnitModel(){
    for (int i = 0; i < unit.length; i++)
      addElement(unit[i]);
    }
  }
}
