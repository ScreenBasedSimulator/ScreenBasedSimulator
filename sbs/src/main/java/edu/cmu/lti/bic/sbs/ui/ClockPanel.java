package edu.cmu.lti.bic.sbs.ui;

import javax.swing.*;

/**
 * 
 * @author Xiangzhu Long
 * 
 */
public class ClockPanel extends JPanel {
  JLabel timeLabel = null;
  
  int second = 0;
  int minute = 0;
  int hour = 0;
  
  public void setTime(int h, int m, int s){
    second = s;
    minute = m;
    hour = h;
   
    timeLabel.setText(hour + ":" + minute + ":" + second);
  }
  
  public ClockPanel(){
	timeLabel =  new JLabel();
    timeLabel.setText(0 + "");
    this.add(timeLabel);
    this.setTime(12, 0, 0);    
  }
}
