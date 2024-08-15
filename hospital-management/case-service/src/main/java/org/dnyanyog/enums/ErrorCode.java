package org.dnyanyog.enums;

public enum ErrorCode {
  CASE_ADDED_SUCCESS("200", "case Added successfully"),
  CASE_UPDATE_SUCCESS("200", "Case updated sucessfully"),
  CASE_DELETE_SUCCESS("200", "Case Deleted Successfully"),
  CASE_NOT_FOUND("404", "Case Not Found"),
  CASE_FOUND("200", "case found"),
  CASE_UPDATE_FAILURE("500", "Failed to update case");

  public final String status;
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
