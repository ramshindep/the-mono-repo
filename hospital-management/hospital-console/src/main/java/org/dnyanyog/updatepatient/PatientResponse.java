package org.dnyanyog.updatepatient;


public class PatientResponse {

  private String Status;
  private String Message;
  private String patientId;

  
  private PatientData patientData;

  public String getStatus() {
    return Status;
  }

  public void setStatus(String status) {
    Status = status;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

  public PatientData getPatientData() {
    return patientData;
  }

  public void setPatientData(PatientData patientData) {
    this.patientData = patientData;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }
}
