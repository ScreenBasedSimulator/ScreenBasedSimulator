package edu.cmu.lti.bic.sbs.gson;

public class Record {
	String user;
	int scenarioId;
	Float score;
	String report = "N/A";
	String debrief = "N/A";
	public Record(String user, int scenarioId, Float score, String report, String debrief) {
		this.user = user;
		this.scenarioId = scenarioId;
		this.score = score;
		this.report = report;
		this.debrief = debrief;
	}
	@Override
	public String toString() {
		String text = 
				"<br/>" +
						"<p>User: " + user + "</p>" + 
						"<p>Score: " + score + "</p>" + 
						"<p> Report: " + report.replace("\n", "<br/>") + "</p>" + 
						"<p> Debreif:" + debrief + "</p>" + 
						"<p> Scenario:" + scenarioId + "</p>" + 
				"<br/>";
		return text;
	}
}
