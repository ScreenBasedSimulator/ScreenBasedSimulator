package edu.cmu.lti.bic.sbs.ui.monitor;

import javax.swing.*;
/**
 * 
 * @author Guan Wang
 *
 */
public class RespiratoryRatePanel extends JPanel {
	private JLabel respiratoryRateLabel=null;
	private final static int width=17;
	private final static int height=3;
	public RespiratoryRatePanel(){
		respiratoryRateLabel=new JLabel("RR: ?");
		this.add(respiratoryRateLabel);
	}
	public void setRespiratoryRate(int respiratoryRateData){
		respiratoryRateLabel.setText("RR: "+respiratoryRateData+"");
	}
}
