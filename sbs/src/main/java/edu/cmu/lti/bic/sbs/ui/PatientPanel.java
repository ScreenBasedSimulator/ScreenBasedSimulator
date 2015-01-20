package edu.cmu.lti.bic.sbs.ui;

import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.component.*;
import com.googlecode.lanterna.terminal.TerminalSize;

public class PatientPanel extends Panel {
    static int PANELSIZE = 70;
    Panel basicPanel = new Panel(new Border.Bevel(true), Panel.Orientation.HORISONTAL);
    Panel descriptionPanel = new Panel(new Border.Bevel(true), Panel.Orientation.HORISONTAL);
  
    Label basicLabel = new Label();
    Label descriptionLabel = new Label();
    
    String basic = "basic";
    String description = "description";
  
  public PatientPanel(){
    super(new Border.Standard(), Panel.Orientation.VERTICAL);
    super.setTitle("Patient Information");
    setBasicPanel();
    super.addComponent(basicPanel);
    setDescriptionPanel();
    super.addComponent(descriptionPanel);
  }
  
  public void setBasicPanel(){
    basicPanel.setTitle("BASIC");
    basicLabel.setText(process(basic));
    basicPanel.addComponent(basicLabel);
    basicPanel.setPreferredSize(new TerminalSize(PANELSIZE,10));
  }
  
  public void setDescriptionPanel(){
    descriptionPanel.setTitle("DESCRIPTION");
    descriptionLabel.setText(process(description));
    descriptionPanel.addComponent(descriptionLabel);
    descriptionPanel.setPreferredSize(new TerminalSize(PANELSIZE,50));
  }
  
  public String process(String str) {
    if(str.length() < PANELSIZE - 35){
      return str;
    }
    
    String[] arr = str.split(" ");
    StringBuffer sb = new StringBuffer();
    int count = 0;
    for(String s: arr){
      if((count + s.length()) <= PANELSIZE - 35){
        count += s.length();
        if(count + 1 > PANELSIZE - 35){
          sb.append(s + "\n");
          count = 0;
        }
        else{
          count++;
          sb.append(s + " ");
        }
      } else {
        for(int i = 0; i < PANELSIZE - 35 - count; i++){
          sb.append(" ");
        }
        sb.append("\n");
        sb.append(s + " ");
        count = s.length() + 1;
      }
    }
    return sb.toString();
  }

  void setDescription(String str){
    description = str;
//    System.out.println(process(description));
    String tmp = process(description);
    System.out.println(tmp);
    descriptionLabel.setText(tmp);
  }
  
  void setBasic(String str){
    basic = str;
    basicLabel.setText(process(basic));
  }
}
