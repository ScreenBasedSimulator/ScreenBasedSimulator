package edu.cmu.lti.bic.sbs.ui;

import com.googlecode.lanterna.gui.*;
import com.googlecode.lanterna.terminal.TerminalSize;

public class MyWindow extends Window
{ 
    public MyWindow()
    {    
        super("Window");
        PatientPanel pp = new PatientPanel();

        this.addComponent(pp);
        this.setWindowSizeOverride(new TerminalSize(50,100));
        
        pp.setBasic("come on");
        pp.setDescription("Neither Lacks nor her family gave Lacks's physician permission to harvest the cells, but, at that time, permission was neither required nor customarily sought.");
    }
}
