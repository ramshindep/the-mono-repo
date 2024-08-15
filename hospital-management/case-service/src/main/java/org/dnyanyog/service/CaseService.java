package org.dnyanyog.service;

import org.dnyanyog.dto.AddCaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.dto.UpdateCaseRequest;

public interface CaseService {

  public CaseResponse addCase(AddCaseRequest addRequest);

  public CaseResponse updateCase(UpdateCaseRequest updateRequest);

  public CaseResponse deleteCase(String caseNumber);

  public CaseResponse getCaseByCaseId(String caseNumber);

  public CaseResponse getCaseByPatientId(String patientId);
}
