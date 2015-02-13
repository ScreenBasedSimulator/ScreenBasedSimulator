package edu.cmu.lti.bic.sbs.engine;

import java.util.TimerTask;

public class CoreTimerTask extends TimerTask {
	private Engine engine;
	private int interval;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		engine.update(interval);
	}
	
	public CoreTimerTask(int interval, Engine engine){
		this.engine = engine;
		this.interval = interval;
	}

}
