package edu.cmu.lti.bic.sbs.ui.menu;

import javax.swing.*;

public class UIMenuPanel extends JPanel{
	public UIMenuPanel(){
		this.add(new JLabel("What you can do in the simu shows up here!"));
	}
	public void updateMenu(Menu menu){
		for(int i=0;i<menu.actions.size();i++){
			//actionList.add(new Label(menu.actions.get(i)));
			this.add(new JLabel(menu.actions.get(i)));
		}
	}
}
