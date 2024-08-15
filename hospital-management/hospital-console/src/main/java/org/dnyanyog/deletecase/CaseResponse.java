package org.dnyanyog.deletecase;


public class CaseResponse {

  private String message;
  private String status;

  private CaseData caseData;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CaseData getCaseData() {
    return caseData;
  }

  public void setCaseData(CaseData caseData) {
    this.caseData = caseData;
  }
}
