package edu.cmu.lti.bic.sbs.ui.menu;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;


public class menuWindow extends Window{
	UIMenuPanel newPanel=null;
	public menuWindow(String title){
		super(title);
		newPanel=new UIMenuPanel();
		this.addComponent(newPanel);
		Menu menu=new Menu();
		menu.addActions("1) you can inject patient.");
		menu.addActions("2) you can ask nurse for help.");
		newPanel.updateMenu(menu);
	}
	public static void main(String[] args) {
		menuWindow m=new menuWindow("new");
	    GUIScreen gui = TerminalFacade.createGUIScreen();
	    if(gui == null) {
	        System.err.println("Couldn't allocate a terminal!");
	        return;
	    }
	    gui.getScreen().startScreen();
	    gui.showWindow(m,GUIScreen.Position.NEW_CORNER_WINDOW);
	    gui.getScreen().stopScreen();
	}
	
}
