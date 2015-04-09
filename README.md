# ScreenBasedSimulator

## Signatures

### UI
engine -> ui 

* updateTime(Time)
* setPatientInfo(Patient)
* addDrug(Drug)
* addTool(Tool)
* updateMonitor(int HeartRate, int respirRate, int lowBP, int HighBP, double oxyLevel)
* update(MedPara)
* updateReport(String report)
* updatePathography(String pathography)
* updateSound(Sound)

ui -> engine

* callCode(String code)
* boolean connectMonitor()
* useEquipment(equipment)
* useDrug(Drug, double dose)

### EVALUATOR

* engine -> evaluator
* receivePara(MedPara)
* receive(Drug, double dose)
* receive(Equipment)

evaluator -> engine
sendReport(String report)

### SIMULATE

simulation&patient -> engine
* simPatient(Patient)

engine -> simulation&patient
* simEquipment(equipment)
* simDrug(Drug, double dose)

