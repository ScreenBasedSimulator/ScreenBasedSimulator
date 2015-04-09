package edu.cmu.lti.bic.sbs.ui;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class PathographyPanel extends JScrollPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7645779128926528004L;
	DefaultListModel<String> model;
	JList<String> list;
	boolean needScroll = false;
	
	public PathographyPanel() {

		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		
		this.setViewportView(list);
		this.setAutoscrolls(true);
		this.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
	        public void adjustmentValueChanged(AdjustmentEvent e) {  
	        	if(needScroll) {
	        		e.getAdjustable().setValue(e.getAdjustable().getMaximum()); 
	        		needScroll = false;
	        	} else  {
	        		e.getAdjustable().setValue(e.getAdjustable().getValue());
	        	}
	        }
	    }); 
	}

	public void addRecord(String s) {
		model.addElement(s);
		needScroll = true;
	}
	
	public void clearRecord() {
		model.clear();
	}
}
