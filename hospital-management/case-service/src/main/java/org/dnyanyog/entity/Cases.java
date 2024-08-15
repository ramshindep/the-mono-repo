package org.dnyanyog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.dnyanyog.utility.CustomIdGenerator;
import org.springframework.stereotype.Component;

@Entity
@Table
@Component
public class Cases {

  @Id private String caseNumber;
  @Column private String patientName;
  @Column private String patientId;
  @Column private String examinationDate;
  @Column private String symptoms;
  @Column private String prescription;
  @Column private String caseStatus;

  public static final String ACTIVE = "ACTIVE";
  public static final String INACTIVE = "INACTIVE";

  public static Cases getInstance() {
    return new Cases();
  }

  public Cases build() {
    return Cases.this;
  }

  public String getCaseNumber() {
    return caseNumber;
  }

  public Cases setCaseNumber(String caseNumber) {
    this.caseNumber = caseNumber;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public Cases setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getPatientId() {
    return patientId;
  }

  public Cases setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public Cases setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public Cases setSymptoms(String symptoms) {
    this.symptoms = symptoms;
    return this;
  }

  public String getPrescription() {
    return prescription;
  }

  public Cases setPrescription(String prescription) {
    this.prescription = prescription;
    return this;
  }

  public String getCaseStatus() {
    return caseStatus;
  }

  public Cases setCaseStatus(String caseStatus) {
    this.caseStatus = caseStatus;
    return this;
  }

  @PrePersist
  private void generateUserId() {
    if (this.caseNumber == null) {
      this.caseNumber = CustomIdGenerator.generatePatientId();
    }
  }
}
