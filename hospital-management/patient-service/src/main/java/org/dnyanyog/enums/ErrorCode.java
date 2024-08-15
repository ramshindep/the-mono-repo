package org.dnyanyog.enums;

public enum ErrorCode {
  PATIENT_ADD_SUCCESS("200", "patient added suceessfully"),
  PATIENT_UPDATE_SUCCESS("200", "patient update suceess"),
  PATIENT_DELETE_SUCCESS("200", "patient will be deleted"),
  PATIENT_NOTFOUND("404", "patient not found"),
  PATIENT_FOUND("200", "patient found");

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
