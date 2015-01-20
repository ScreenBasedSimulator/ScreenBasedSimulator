package edu.cmu.lti.bic.sbs.ui;

import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.gui.component.*;

/**
 * 
 * @author Xiangzhu Long
 * 
 */
public class ClockPanel extends Panel {
  Panel panel = new Panel(new Border.Bevel(true), Panel.Orientation.VERTICAL);
  Label timeLabel = new Label();
  
  int second = 0;
  int minute = 0;
  int hour = 0;
  
  void clearTime(){ 
    panel.removeAllComponents();
  }
  
  public void updateTime(int h, int m, int s){
    second = s;
    minute = m;
    hour = h;
    timeLabel.setText(hour + ":" + minute + ":" + second);
  }
  
  public ClockPanel(){
    super(new Border.Standard(), Panel.Orientation.HORISONTAL);
    timeLabel.setText(0 + "");
    this.addComponent(timeLabel);
    this.updateTime(12, 0, 0);    
  }
}
