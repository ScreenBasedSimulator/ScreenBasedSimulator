package edu.cmu.lti.bic.sbs.simulator;

import java.util.ArrayList;
import java.util.HashMap;

import edu.cmu.lti.bic.sbs.gson.Patient;
import edu.cmu.lti.bic.sbs.gson.Prescription;
import edu.cmu.lti.bic.sbs.gson.Tool;

/*
 * Class Simulator stores the patient model of Oxygen Level, Blood Pressure, 
 * Heart Rate and Respiration Rate
 */
public class Simulator {

	// the model of patient
	private Patient patient;

	static boolean checkBloodPressure = false;
	static boolean checkHeartRate = false;
	static boolean checkOxygenLevel = false;
	static boolean checkRespirationRate = false;
	
	// toolList and pescriptionList to store the information of tool and
	// prescription which have effects on the patient.
	private ArrayList<Tool> toolList;
	private ArrayList<Prescription> prescriptionList;

	// dictionary of boundaries for each parameter
	private static HashMap<String, Double> lowerBoundDict = new HashMap<String, Double>();
	private static HashMap<String, Double> upperBoundDict = new HashMap<String, Double>();

	// the initialization function for engine to involve
	// adding default value for each parameters
	public Patient initialPatient() {
		return initialPatient(90.0, 60.0, 90.0, 0.8, 16.0);
		
		/*patient = new Patient();
		// pt.getBloodPressure().setBpNum(defaultBp);
		patient.getBloodPressure().setSystolicBloodPressure(90.0);
		patient.getBloodPressure().setDiastolicBloodPressure(60.0);
		patient.getHeartRate().setHrNum(90.0);
		patient.getOxygenLevel().setOlNum(0.8);
		patient.getRepirationRate().setRrNum(16.0);
		return patient;*/
	}

	/*
	 * Initialize patient with given parameters
	 */
	public Patient initialPatient(double systolicBloodPressure,
			double diastolicBloodPressure, double heartRate,
			double oxygenLevel, double repirationRate) {
		patient = new Patient();
		
		patient.setBloodPressure(new BloodPressure(
				systolicBloodPressure, lowerBoundDict
						.get("systolicBloodPressure"),
				upperBoundDict.get("systolicBloodPressure"),
				diastolicBloodPressure, lowerBoundDict
						.get("diastolicBloodPressure"),
				upperBoundDict.get("diastolicBloodPressure")));
		
		patient.setHeartRate(new HeartRate(heartRate,
				lowerBoundDict.get("heartRate"), upperBoundDict
						.get("heartRate")));
		
		patient.setOxygenLevel(new OxygenLevel(oxygenLevel,
				lowerBoundDict.get("oxygenLevel"), upperBoundDict
						.get("oxygenLevel")));

		patient.setRespirationRate(new RespirationRate(repirationRate,
				lowerBoundDict.get("respirationRate"), upperBoundDict
						.get("respirationRate")));

		return patient;
	}

	private void initBound() {
		// set default boundaries for each parameters
		lowerBoundDict.put("respirationRate", 10.0);
		upperBoundDict.put("respirationRate", 20.0);

		lowerBoundDict.put("heartRate", 50.0);
		upperBoundDict.put("heartRate", 120.0);

		lowerBoundDict.put("diastolicBloodPressure", 40.0);
		upperBoundDict.put("diastolicBloodPressure", 100.0);

		lowerBoundDict.put("systolicBloodPressure", 70.0);
		upperBoundDict.put("systolicBloodPressure", 190.0);

		lowerBoundDict.put("oxygenLevel", 0.6);
		upperBoundDict.put("oxygenLevel", 1.0);
	}

	public Simulator() {
		super();
		initialPatient();
		initBound();
		toolList = new ArrayList<Tool>();
		prescriptionList = new ArrayList<Prescription>();
	}

	public Simulator(Patient pt) {
		super();
		initBound();
		this.patient = pt;
		toolList = new ArrayList<Tool>();
		prescriptionList = new ArrayList<Prescription>();
	}

	// the engine can get patient info from simulator
	public Patient simPatient() {

		// no tool and no prescription, invoke the downFunction for all four
		// parameters
		if (toolList.size() == 0 && prescriptionList.size() == 0) {
			// invoke the downFunction for all four parameters
			downFunctionOxygenLevel();
			downFunctionHeartRate();
			downFunctionBloodPressure();
			downFunctionBloodPressure();
			downFunctionRespirationRate();
			return this.patient;
		} else {
			
			if (!checkBloodPressure) {
				downFunctionBloodPressure();
			}
			if (!checkHeartRate) {
				downFunctionHeartRate();
			}
			if (!checkOxygenLevel) {
				downFunctionOxygenLevel();
			}
			if (!checkRespirationRate) {
				downFunctionRespirationRate();
			}
			
			// get current value for all four medical parameters
			double currentOxygenLevel = patient.getOxygenLevel().getOlNum();
			double currentHeartRate = patient.getHeartRate().getHrNum();
			double currentSystolicBloodPressure = patient.getBloodPressure()
					.getSystolicBloodPressure();
			double currentDiastolicBloodPressure = patient.getBloodPressure()
					.getDiastolicBloodPressure();
			double currentRespirationRate = patient.getRepirationRate()
					.getRrNum();
			if (toolList.size() > 0) {
				// get tool information
				Tool currentTool = toolList.get(toolList.size() - 1);
				double currentValue = currentTool.getValue();

				// check the tool according to the name of the tool
				if (currentTool.getId().equals("OxygenMask")) {
					double resultOxygenLevel = ytFunctionOxygenLevel(currentValue);
					// System.out.print("resultOL = " + resultOl);
					// reset the oxygenLevel of the patient
					
					patient.getOxygenLevel().setOlNum(resultOxygenLevel);
					checkOxygenLevel = true;
				}

				// the name of the tool do not correct, I just test the function
				if (currentTool.getName().equals("increase the heart rate")) {
					//
					double resultRatioForHeartRate = ytFunctionHeartRate(currentValue);
					System.out.print("resultHR = " + resultRatioForHeartRate);
					double resultHeartRate = (1 + resultRatioForHeartRate / 100)
							* currentHeartRate;

					// System.out.print("patient's heart rate:" +
					// resultHeartRate);
					patient.getHeartRate().setHrNum(resultHeartRate);
				}

				// the name of the tool do not correct, I just test the function
				if (currentTool.getName().equals("increase the blood pressure")) {
					//
					double resultRatioForBloodPressure = ytFunctionBloodPressure(currentValue);
					// System.out.print("resultBR = "
					// + resultRatioForBloodPressure);
					double resultSystolicBloodPressure = (1 + resultRatioForBloodPressure / 100)
							* currentSystolicBloodPressure;
					double resultDiastolicBloodPressure = (1 + resultRatioForBloodPressure / 100)
							* currentDiastolicBloodPressure;

					// System.out.print("patient's Systolic blood pressure:"
					// + resultSystolicBloodPressure);
					// System.out.println("patient's Diastolic blood pressure:"
					// + resultDiastolicBloodPressure);

					patient.getBloodPressure().setDiastolicBloodPressure(resultDiastolicBloodPressure);
					patient.getBloodPressure().setSystolicBloodPressure(resultSystolicBloodPressure);
				}

				// the name of the tool do not correct, I just test the function
				if (currentTool.getName().equals(
						"increase the Respiration Rate")) {
					//
					double resultRatioForRespirationRate = ytFunctionRespirationRate(currentValue);
					System.out.print("resultRR = "
							+ resultRatioForRespirationRate);
					double resultRespirationRate = (1 + resultRatioForRespirationRate / 100)
							* currentRespirationRate;

					// set boundery for respirationRate
					if (resultRespirationRate > 20) {
						resultRespirationRate = 20;
					}

					// System.out.print("patient's Systolic Respiration Rate:"
					// + resultRespirationRate);
					patient.getRepirationRate().setRrNum(resultRespirationRate);
				}
			}

			// check the prescription
			if (prescriptionList.size() > 0) {
				// get the current prescription
				Prescription currentPrescription = prescriptionList
						.get(prescriptionList.size() - 1);
				double currentDoes = currentPrescription.getDose();

				// get prescription information
				if (currentPrescription.getDrug().getId().equals("naloxone")) {
					double resultOxygenLevel = ytFunctionOxygenLevel(currentDoes * 10);
					// System.out.print("resultOL = " + resultOl);
					patient.setOxygenLevel(new OxygenLevel(resultOxygenLevel,
							lowerBoundDict.get("oxygenLevel"), upperBoundDict
									.get("oxygenLevel")));
					
					checkOxygenLevel = true;
				}

				// **********************the prescription
				// effect**************************************************

				if (currentPrescription.getDrug().getId().equals("naloxone")) {
					//
					double resultRatioForHeartRate = ytFunctionHeartRate(currentDoes);

					// System.out.print("resultHR = " +
					// resultRatioForHeartRate);

					double resultHeartRate = (1 + resultRatioForHeartRate / 100)
							* currentHeartRate;

					// System.out.print("patient's heart rate:" +
					// resultHeartRate);

					patient.getHeartRate().setHrNum(resultHeartRate);
					checkHeartRate = true;
				}

				// the name of the tool do not correct, I just test the function
				if (currentPrescription.getDrug().getId().equals("naloxone")) {
					//
					double resultRatioForBloodPressure = ytFunctionBloodPressure(currentDoes);

					// System.out.print("resultBR = " +
					// resultRatioForBloodPressure);

					double resultSystolicBloodPressure = (1 + resultRatioForBloodPressure / 100)
							* currentSystolicBloodPressure;
					double resultDiastolicBloodPressure = (1 + resultRatioForBloodPressure / 100)
							* currentDiastolicBloodPressure;

					// System.out.print("patient's Systolic blood pressure:"+
					// resultSystolicBloodPressure);
					// System.out.println("patient's Diastolic blood pressure:"
					// + resultDiastolicBloodPressure);

					patient.getBloodPressure().setDiastolicBloodPressure(resultDiastolicBloodPressure);
					patient.getBloodPressure().setSystolicBloodPressure(resultSystolicBloodPressure);
				
					checkBloodPressure = true;
				}

				// the name of the tool do not correct, I just test the function
				if (currentPrescription.getDrug().getId().equals("naloxone")) {
					//
					double resultRatioForRespirationRate = ytFunctionRespirationRate(currentDoes);

					// System.out.print("resultRR = "
					// + resultRatioForRespirationRate);

					double resultRespirationRate = (1 + resultRatioForRespirationRate / 100)
							* currentRespirationRate;

					// System.out.print("patient's Systolic Respiration Rate:" +
					// resultRespirationRate);

					patient.getRepirationRate().setRrNum(resultRespirationRate);
					checkRespirationRate = true;
				}
			}

			return patient;
		}
	}

	public Patient getPatient() {
		return patient;
	}

	// reset patient
	public void setPatient(Patient patient) {
		this.patient = patient;
		this.prescriptionList.clear();
		this.toolList.clear();
		System.out.println("reset pt in simulator!!!!!");
	}

	public ArrayList<Tool> getToolList() {
		return toolList;
	}

	public void setToolList(ArrayList<Tool> toolList) {
		this.toolList = toolList;
	}

	public ArrayList<Prescription> getPrescriptionList() {
		return prescriptionList;
	}

	public void setPrescriptionList(ArrayList<Prescription> prescriptionList) {
		this.prescriptionList = prescriptionList;
	}

	public void simulateWithTool(Tool eq) {
		// set the parameters according to the equipment from engine
		System.out.println("using equipments in the function simulateWithTool");

		// when the engine involve the function simulateWithTool, the simulator
		// add the tool to the toolList
		toolList.add(eq);
	}

	public void simWithDrugs(Prescription p) {
		// set the parameters according to the drug from engine
		System.out.println("using drug in the function simulateWithDrug");

		// when the engine involve the function simulateWithDrug, the simulator
		// add the Prescription to the prescriptionList
		prescriptionList.add(p);
	}

	// helper function
		double fFunction(double x) {
			double p1 = 1.667 * Math.pow(10, -6);
			double p2 = -0.0002536;
			double p3 = 0.01458;
			double p4 = -0.2743;

			double result;

			result = p1 * Math.pow(x, 3) + p2 * Math.pow(x, 2) + p3 * x + p4;

			return result;
		}

		// helper function
		public double t0Function(double x0) {
			double result;

			result = 1.0 * (120 - x0) / 2 - 1.0 / fFunction(x0)
					* Math.log(1.0 * (1 - patient.getOxygenLevel().getOlNum()) / //
							patient.getOxygenLevel().getOlNum());

//			System.out.println("1.0*(120 - x0)/2 = " + 1.0 * (120 - x0) / 2);
//			System.out.println("1.0/fFunction(x0) = " + 1.0 / fFunction(x0));
//			System.out.println("Math.log(1.0*(1-defaultOl) / defaultOl = " + //
//					Math.log(1.0 * (1 - patient.getOxygenLevel().getOlNum())
//							/ patient.getOxygenLevel().getOlNum()));
	//
//			System.out.println("t0Function's result:" + result);

			return result;
		}

		// this function calculate the curve(oxygenLevel)
		public double ytFunctionOxygenLevel(double x0) {
			double result;
			// invoke two helper function
			result = 1.0 / (1 + Math.exp(-fFunction(x0)
					* ((t0Function(x0) + 1.0 / 10) - 1.0 * (120 - x0) / 2)));
			if (result < 1.00) {
				this.patient.setOxygenLevel(new OxygenLevel(result));
			}
			// set a bound to oxgen level
			else {
				result = 1.00;
				this.patient.setOxygenLevel(new OxygenLevel(result));
			}
			return result;
		}

		// this function calculate the curve(BloodPressure)
		public double ytFunctionBloodPressure(double x0) {
			double result;
			result = 1.0 / (1 + Math.exp(-fFunction(x0)
					* ((t0Function(x0) + 1.0 / 15) - 1.0 * (120 - x0) / 2)));
			return result;
		}

		// this function calculate the curve(HeartRate)
		public double ytFunctionHeartRate(double x0) {

			double result;
			result = 1.0 / (1 + Math.exp(-fFunction(x0)
					* ((t0Function(x0) + 1.0 / 17) - 1.0 * (120 - x0) / 2)));
			return result;
		}

		// this function calculate the curve(RespirationRate)
		public double ytFunctionRespirationRate(double x0) {

			double result;

			result = 1.0 / (1 + Math.exp(-fFunction(x0)
					* ((t0Function(x0) + 1.0 / 8) - 1.0 * (120 - x0) / 2)));

			return result;
		}

		// invoke the function OxygenLevel when there is no action
		public double downFunctionOxygenLevel() {
			double result;
			result = 1 - 0.0015 * Math.pow(1, 2);
			double currentOxygenLevel = patient.getOxygenLevel().getOlNum();

			double resultOxygenLevel = currentOxygenLevel * result;

			// set the boundery for the oxygenLevel
			if (resultOxygenLevel < 0.6) {
				resultOxygenLevel = 0.6;
			} else {
				// doing nothing
			}

			// reset the oxygenLevel of the patient
			this.patient.setOxygenLevel(new OxygenLevel(resultOxygenLevel));
			return result;
		}

		// invoke the function BloodPressure when there is no action
		public double downFunctionBloodPressure() {
			double result;
			result = 1 - 0.0025 * Math.pow(1, 2);
			double currentSystolicBloodPressure = patient.getBloodPressure()
					.getSystolicBloodPressure();
			double currentDiastolicBloodPressure = patient.getBloodPressure()
					.getDiastolicBloodPressure();

			double resultSystolicBloodPressure = result
					* currentSystolicBloodPressure;
			double resultDiastolicBloodPressure = result
					* currentDiastolicBloodPressure;

			// set the boundery for the bloodPressure
			if (resultSystolicBloodPressure < 70) {
				resultSystolicBloodPressure = 70;
			} else {
				// doing nothing
			}
			if (resultDiastolicBloodPressure < 40) {
				resultDiastolicBloodPressure = 40;
			} else {
				// doing nothing
			}

			// System.out.println("currentSystolicBloodPressure = "
			// + currentSystolicBloodPressure);
			// System.out.println("currentDiastolicBloodPressure = "
			// + currentDiastolicBloodPressure);

			// reset the SystolicBloodPressure and DiastolicBloodPressure of the
			// patient
			this.patient.setBloodPressure(new BloodPressure(
					resultSystolicBloodPressure, resultDiastolicBloodPressure));

			return result;
		}

		// invoke the function HeartRate when there is no action
		public double downFunctionHeartRate() {
			double result;
			result = 1 - 0.0025 * Math.pow(1, 2);
			double currentHeartRate = patient.getHeartRate().getHrNum();
			// reset the heartRate of the patient

			double resultHeartRate = currentHeartRate * result;

			// set the boundery
			if (resultHeartRate < 50) {
				resultHeartRate = 50;
			} else {
				// doing nothing
			}

			this.patient.setHeartRate(new HeartRate(resultHeartRate));
			return result;
		}

		// invoke the function RespirationRate when there is no action
		public double downFunctionRespirationRate() {
			double result;
			result = 1 - 0.001 * Math.pow(1, 2);
			double currentRespirationRate = patient.getRepirationRate().getRrNum();

			double resultRespirationRate = currentRespirationRate * result;

			if (resultRespirationRate < 10) {
				resultRespirationRate = 10;
			} else {
				// doing nothing
			}
			// reset the respirationRate of the patient
			this.patient.setRespirationRate(new RespirationRate(
					resultRespirationRate));
			return result;
		}

		/*
		 * public double newSimulationFunction(double value){ init_spo2 =
		 * linspace(1,0.7,6); init_resp = linspace(70,40,6); % f_hr = fit(m',
		 * init_hr','poly1'); % f_spo2 = fit(m', init_spo2','poly1'); % f_resp =
		 * fit(m', init_resp','poly1');
		 * 
		 * % simulate bp -> f(hr) hrr = linspace(120,50,30); bpp =
		 * linspace(170,80,30); f_bp_hr = fit(hrr',bpp','poly1');
		 * 
		 * % simulate bp -> f(resp) hrr = linspace(120,50,30); resp =
		 * linspace(30,10,30); f_resp_hr = fit(hrr',resp','poly1'); %% User specify:
		 * % morphin-->m [0 5] % naloxone-->n [0 10] % initial HR [120 50], 120 is
		 * normal % BP [170:60] % RESP [30:10]
		 * 
		 * clear;clc % When choosing naloxone, n can be 0-10. If both 5, naloxone
		 * will just % keep the status steady, but not increase the parameters m =
		 * 5; n = 9; init_hr = 89;
		 * 
		 * % Simulation delta_t = 1; % engine cycle time init_rate = 1/10*(n-m);
		 * mask = false; while true rate_next =
		 * sign(init_rate)*exp(log(abs(init_rate))-1/70*delta_t); hr =
		 * init_hr+rate_next*delta_t; init_rate = rate_next; init_hr = hr; if hr >
		 * 120 hr = 120; elseif hr < 50 % die hr = 50; end
		 * 
		 * % Simulate Blood Pressure and Respiration from HeartRate % range of HR
		 * [120:50]--> BP [170:60] % range of HR [120:50]--> RESP [30:10] bp =
		 * 1.286*hr-15.17;
		 * 
		 * if mask == true resp = 0.2875*hr-4.286+mask_rate*delta_t; else resp =
		 * 0.2875*hr-4.286; end % only round the parameters when display to UI!!!
		 * fprintf('Heart rate: %3.0f \n',floor(hr)); % report hr fprintf('Blood
		 * pressure: %3.0f \n',floor(bp)); % report bp fprintf('Respiration: %3.0f
		 * \n',floor(resp)); % report resp pause clc end
		 * 
		 * }
		 */

}