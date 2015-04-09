package edu.cmu.lti.bic.sbs.ui.monitor;

import javax.swing.*;
/**
 * 
 * @author Guan Wang
 *
 */
public class RespiratoryRatePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2299248968540717977L;
	private JLabel respiratoryRateLabel=null;

	public RespiratoryRatePanel(){
		respiratoryRateLabel=new JLabel("RR: ?");
		this.add(respiratoryRateLabel);
		this.setOpaque(false);
	}
	public void setRespiratoryRate(int respiratoryRateData){
		respiratoryRateLabel.setText("RR: "+respiratoryRateData+"");
	}
}
