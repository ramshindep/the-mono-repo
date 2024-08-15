package org.dnyanyog.enums;

public enum ErrorCode {
  APPOINTMENT_ADD_SUCCESS("200", "appointment added successfully"),
  APPOINTMENT_NOT_FOUND("404", "appoinment not found"),
  APPOINTMENT_UPDATE_SUCCESS("200", "Appointment updated successfully"),
  APPOINTMENT_DELETE_SUCCESS("200", "appointment deleted successfully"),
  APPOINTMENT_FOUND_SUCCESS("200", "appointment found successfully"),
  APPOINTMENT_UPDATE_FAILURE("500", "Failed to update appointment");

  private final String status;
  private final String message;

  ErrorCode(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
