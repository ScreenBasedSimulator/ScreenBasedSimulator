package edu.cmu.lti.bic.sbs.ui;

public class UserInterfaceInitializationException extends Exception {
	/**
	 * @author Xiao Han
	 */
	private static final long serialVersionUID = -6310008036438675305L;

	public UserInterfaceInitializationException(String msg){
		super("User Interface Initalization Error:" + msg);
	}
}
