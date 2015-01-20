package edu.cmu.lti.bic.sbs.ui.menu;

import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;

public class UIMenuPanel extends Panel{
	public UIMenuPanel(){
		super(new Border.Bevel(true),Panel.Orientation.VERTICAL);
		this.addComponent(new Label("What you can do in the simu shows up here!"));
	}
	public void updateMenu(Menu menu){
		this.removeAllComponents();
		for(int i=0;i<menu.actions.size();i++){
			//actionList.add(new Label(menu.actions.get(i)));
			this.addComponent(new Label(menu.actions.get(i)));
		}
	}
}
