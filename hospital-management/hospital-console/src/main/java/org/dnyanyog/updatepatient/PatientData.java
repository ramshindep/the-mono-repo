package org.dnyanyog.updatepatient;



public class PatientData {

   private String patientId;
   private String patientName;
   private String patientNameMarathi;
   private String mobileNumber;
   private String Gender;
   private String birthDate;
   private String firstExaminationDate;
   private String address;
   private String status;
   
   
   public String getPatientId() {
	    return patientId;
	  }

	  public void setPatientId(String patientId) {
	    this.patientId = patientId;
	  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getPatientNameMarathi() {
    return patientNameMarathi;
  }

  public void setPatientNameMarathi(String patientNameMarathi) {
    this.patientNameMarathi = patientNameMarathi;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getGender() {
    return Gender;
  }

  public void setGender(String gender) {
    Gender = gender;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getFirstExaminationDate() {
    return firstExaminationDate;
  }

  public void setFirstExaminationDate(String firstExaminationDate) {
    this.firstExaminationDate = firstExaminationDate;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
