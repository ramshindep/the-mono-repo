package org.dnyanyog.updateappointment;

public class AppointmentResponse {

  private String status;
  private String message;

  private AppointmentData appointmentData;

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public AppointmentData getAppointmentData() {
    return appointmentData;
  }

  public void setAppointmentData(AppointmentData appointmentData) {
    this.appointmentData = appointmentData;
  }
}
