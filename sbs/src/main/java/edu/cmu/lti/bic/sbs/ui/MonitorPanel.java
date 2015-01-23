package edu.cmu.lti.bic.sbs.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class MonitorPanel extends JPanel{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonitorPanel window = new MonitorPanel();
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
	public MonitorPanel()  {
		setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		initialize();
	}

	JPanel bloodPressurePanel;
	JPanel heartRatePanel;
	JPanel oxygenLevelPanel;
	JPanel respiratoryRatePanel;
	
	JLabel bloodPressureLabel;
	JLabel heartRateLabel; 
	JLabel oxygenLevelLabel;
	JLabel respiratoryRateLabel; 
	private void initialize() {
		/*frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		*/
		/*JPanel monitorPanel = new JPanel();
		monitorPanel.setBounds(327, 52, 100, 130);
		frame.getContentPane().add(monitorPanel);
		monitorPanel.setLayout(null);*/
		
		bloodPressurePanel = new JPanel();
		bloodPressurePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bloodPressurePanel.setBounds(6, 6, 90, 25);
		this.add(bloodPressurePanel);
		
		heartRatePanel = new JPanel();
		heartRatePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		heartRatePanel.setBounds(6, 37, 90, 25);
		this.add(heartRatePanel);
		
		JPanel oxygenLevelPanel = new JPanel();
		oxygenLevelPanel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "123", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		oxygenLevelPanel.setBounds(6, 70, 90, 25);
		this.add(oxygenLevelPanel);
		
		JPanel respiratoryRatePanel = new JPanel();
		respiratoryRatePanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		respiratoryRatePanel.setBounds(6, 100, 90, 25);
		this.add(respiratoryRatePanel);
		
		bloodPressureLabel = new JLabel("BP: ?/? mmHg");
		bloodPressurePanel.add(bloodPressureLabel);
		
		heartRateLabel = new JLabel("HR: ? bpm");
		heartRatePanel.add(heartRateLabel);
		
		oxygenLevelLabel = new JLabel("SpO2: ? %");
		oxygenLevelPanel.add(oxygenLevelLabel);
		
		respiratoryRateLabel = new JLabel("RR: ?");
		respiratoryRatePanel.add(respiratoryRateLabel);
	}
	public void setBloodPressure(int bloodPressureUpperBound,int bloodPressureLowerBound){
		bloodPressureLabel.setText("BP: "+bloodPressureUpperBound+"/"+bloodPressureLowerBound+" mmHg");
	}
	public void setHeartRate(int heartRate){
		heartRateLabel.setText("HR: "+heartRate+" bpm");
	}
	public void setOxygenLevel(int oxygenLevel){
		oxygenLevelLabel.setText("SpO2: "+oxygenLevel+" %");
	}
	public void setRespiratoryRate(int respiratoryRate){
		respiratoryRateLabel.setText("RR: "+respiratoryRate);
	}
}
