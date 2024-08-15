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
public class Patient {

  @Id private String patientId;

  @Column private String patientName;

  @Column private String patientNameMarathi;

  @Column private String MobileNumber;
  @Column private String Gender;
  @Column private String birthDate;
  @Column private String FirstExaminationDate;

  @Column private String Address;

  @Column private String Status;

  public static final String ACTIVE = "ACTIVE";
  public static final String INACTIVE = "INACTIVE";

  public static Patient getInstance() {
    return new Patient();
  }

  public Patient build() {
    return Patient.this;
  }

  public String getPatientId() {
    return patientId;
  }

  public Patient setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public Patient setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getPatientNameMarathi() {
    return patientNameMarathi;
  }

  public Patient setPatientNameMarathi(String patientNameMarathi) {
    this.patientNameMarathi = patientNameMarathi;
    return this;
  }

  public String getMobileNumber() {
    return MobileNumber;
  }

  public Patient setMobileNumber(String mobileNumber) {
    MobileNumber = mobileNumber;
    return this;
  }

  public String getGender() {
    return Gender;
  }

  public Patient setGender(String gender) {
    Gender = gender;
    return this;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public Patient setBirthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  public String getFirstExaminationDate() {
    return FirstExaminationDate;
  }

  public Patient setFirstExaminationDate(String firstExaminationDate) {
    FirstExaminationDate = firstExaminationDate;
    return this;
  }

  public String getAddress() {
    return Address;
  }

  public Patient setAddress(String address) {
    Address = address;
    return this;
  }

  public String getStatus() {
    return Status;
  }

  public Patient setStatus(String status) {
    Status = status;
    return this;
  }

  @PrePersist
  private void generateUserId() {
    if (this.patientId == null) {
      this.patientId = CustomIdGenerator.generatePatientId();
    }
  }
}
