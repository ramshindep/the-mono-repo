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
public class Appointment {
  @Id private String appointmentId;
  @Column private String patientName;
  @Column private String patientId;
  @Column private String examinationDate;
  @Column private String appointmentTime;
  @Column private String appointmentStatus;

  public static final String PENDING = "PENDING";
  public static final String CANCELLED = "CANCELLED";

  public static Appointment getInstance() {
    return new Appointment();
  }

  public Appointment build() {
    return Appointment.this;
  }

  public String getAppointmentId() {
    return appointmentId;
  }

  public Appointment setAppointmentId(String appointmentId) {
    this.appointmentId = appointmentId;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public Appointment setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getPatientId() {
    return patientId;
  }

  public Appointment setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  public String getExaminationDate() {
    return examinationDate;
  }

  public Appointment setExaminationDate(String examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public Appointment setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
    return this;
  }

  public String getAppointmentStatus() {
    return appointmentStatus;
  }

  public Appointment setAppointmentStatus(String appointmentStatus) {
    this.appointmentStatus = appointmentStatus;
    return this;
  }

  @PrePersist
  private void generateUserId() {
    if (this.appointmentId == null) {
      this.appointmentId = CustomIdGenerator.generatePatientId();
    }
  }
}
