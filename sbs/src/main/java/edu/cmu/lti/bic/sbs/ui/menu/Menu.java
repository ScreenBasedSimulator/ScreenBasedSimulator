package edu.cmu.lti.bic.sbs.ui.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	List<String> actions=null;
	public Menu(){
		actions=new ArrayList<String>();
	}
	public void addActions(String action){
		actions.add(action);
	}
}
