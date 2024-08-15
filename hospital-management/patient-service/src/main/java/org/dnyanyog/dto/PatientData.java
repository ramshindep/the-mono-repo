package org.dnyanyog.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PatientData {

  private String patientId;

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  @NotNull private String patientName;
  @NotNull private String patientNameMarathi;
  @NotNull private String mobileNumber;
  @NotNull private String Gender;
  @NotNull private String birthDate;
  @NotNull private String firstExaminationDate;

  @NotNull private String address;

  @NotNull private String status;

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
