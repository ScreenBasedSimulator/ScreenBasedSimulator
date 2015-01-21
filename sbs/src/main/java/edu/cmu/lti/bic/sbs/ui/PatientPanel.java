package edu.cmu.lti.bic.sbs.ui;
import javax.swing.*;

public class PatientPanel extends JPanel {
    static int PANELSIZE = 70;
    JPanel basicPanel = new JPanel();
    JPanel descriptionPanel = new JPanel();
  
    JLabel basicLabel = new JLabel();
    JLabel descriptionLabel = new JLabel();
    
    String basic = "basic";
    String description = "description";
  
  public PatientPanel(){
    setBasicPanel();
    super.add(basicPanel);
    setDescriptionPanel();
    super.add(descriptionPanel);
  }
  
  public void setBasicPanel(){
    basicLabel.setText(process(basic));
    basicPanel.add(basicLabel);
  }
  
  public void setDescriptionPanel(){
    descriptionLabel.setText(process(description));
    descriptionPanel.add(descriptionLabel);
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
