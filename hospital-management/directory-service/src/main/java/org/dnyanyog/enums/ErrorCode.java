package org.dnyanyog.enums;

public enum ErrorCode {
  USER_ADD_SUCCESS("200", "user added successfully"),
  USER_UPDATE_SUCCESS("200", "USER UPDATED SUCESSFULY"),
  USER_FOUND("200", "user found success"),
  USER_NOT_FOUND("404", "user not found"),
  VALIDATION_SUCCESS("200", "LOGIN SUCCESSFULLY"),
  VALIDATION_FAIL("403", "password  is incorrect"),
  VALIDATION_MOBILENO("403", "mobile no is incorrect"),
  LOGIN_ERROR("401", "incorrect credentials"),
  USER_DELETE_SUCCESS("200", "User status set to inactive"),
  USER_INACTIVE("403", "User account is inactive");

  private final String code;
  private final String message;

  ErrorCode(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
