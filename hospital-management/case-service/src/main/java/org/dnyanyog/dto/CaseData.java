package org.dnyanyog.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Component;

@Component
public class CaseData {
  @NotBlank private String caseNumber;
  @NotBlank private String patientName;
  @NotBlank private String patientId;
  @NotBlank private String examinationDate;
  @NotBlank private String symptoms;
  @NotBlank private String prescription;
  @NotBlank private String caseStatus;

  public String getCaseNumber() {
    return caseNumber;
  }

  public void setCaseNumber(String caseNumber) {
    this.caseNumber = caseNumber;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public void setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  }

  public String getPrescription() {
    return prescription;
  }

  public void setPrescription(String prescription) {
    this.prescription = prescription;
  }

  public String getCaseStatus() {
    return caseStatus;
  }

  public void setCaseStatus(String caseStatus) {
    this.caseStatus = caseStatus;
  }
}
