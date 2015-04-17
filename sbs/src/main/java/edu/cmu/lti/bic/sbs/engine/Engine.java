package edu.cmu.lti.bic.sbs.engine;

import edu.cmu.lti.bic.sbs.evaluator.Evaluator;
import edu.cmu.lti.bic.sbs.gson.Drug;
import edu.cmu.lti.bic.sbs.gson.Report;
import edu.cmu.lti.bic.sbs.gson.Tool;

import java.util.Calendar;
import java.util.Timer;

import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.simulator.Simulator;
import edu.cmu.lti.bic.sbs.ui.UserInterface;

/**
 * The Engine Class
 * 
 * @author Xiaoxu Lu
 *
 */
public class Engine {
	UserInterface ui = null;
	Simulator simulator = null;
	Evaluator evaluator = null;
	Scenario scenario = null;
	State state = null;

	Calendar time = Calendar.getInstance();
	Timer timer = new Timer();

	boolean isOver = false;
	private Report report = null;

	/**
	 * Constructor function, responsible for creating UserInterface, Simulator
	 * and Evaluator for the Scenario, as well as start time looping
	 * 
	 * @throws Exception
	 */
	public Engine() throws Exception {
		// User interface initialization
		try {
			System.out.println("Initializing the user interface");
			ui = new UserInterface(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Scenario initialization
		scenario = new Scenario(ui);

		// load tool, drug and patient through scenario
		Tool[] tools = scenario.readTool();
		for (Tool tool : tools) {
			ui.addTool(tool);
		}
		Drug[] drugMap = scenario.readDrug();
		ui.addDrug(drugMap);
		Patient patient = scenario.readPatient();
		ui.setPatientInfo(patient);
		// state for checkpoint
		state = new State(patient);
		// set simulator and evaluator
		simulator = new Simulator(patient);
		evaluator = new Evaluator(this);
		// Start looping
		timer.scheduleAtFixedRate(new CoreTimerTask(1000, this), 0, 1000);
	}

	public void connectMonitor() {
		scenario.connectMonitor();
	}

	public void useTool(String id) throws Exception {
		Tool tool = scenario.getToolMap().get(id);
		if (tool == null) {
			throw new Exception("No such Tool");
		}
		scenario.useTool(tool, evaluator, simulator, time);
	}

	public void useDrug(String id, Double dose, String unit) throws Exception {
		Drug drug = scenario.getDrugMap().get(id);
		if (drug == null) {
			throw new Exception("No such Drug");
		}
		Prescription p = new Prescription();
		p.setDose(dose).setDrug(drug).setUnit(unit);
		scenario.useDrug(p.getDrug(), p.getDose(), evaluator, simulator, time,
				p);
	}

	public void update(int interval) {
		time.add(Calendar.MILLISECOND, interval);
		scenario.update(ui, evaluator, simulator, state, time);
	}
	
	public void simOver(double score, String content) {
		timer.cancel();
		isOver = true;
		setReport(new Report(score, content));
		ui.updateReport(score, content);
	}
	public void recover(int index) {
		// patient = state.getCheckpoint(index);
	}

	public void restartSim() {
		// scenario.restart(simulator, state);
		// evaluator = new Evaluator(this);
	}

	// setters and getters
	private void setReport(Report report) {
		this.report = report;
	}

	public void setDebrief(String queryParams) {

	}

	public Patient getPatient() {
		return simulator.getPatient();
	}

	public Calendar getTime() {
		return time;
	}

	public Report getReport() {
		return report;
	}

	public boolean isStop() {
		return isOver;
	}

}
