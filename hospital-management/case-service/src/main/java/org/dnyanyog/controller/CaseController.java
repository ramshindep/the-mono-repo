package org.dnyanyog.controller;

import jakarta.validation.Valid;
import org.dnyanyog.dto.AddCaseRequest;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.dto.UpdateCaseRequest;
import org.dnyanyog.service.CaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaseController {

  @Autowired CaseServiceImpl caseService;

  @PostMapping(
      path = "/api/v1/case/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public CaseResponse addCase(@Valid @RequestBody AddCaseRequest addRequest) {
    return caseService.addCase(addRequest);
  }

  @PostMapping(
      path = "/api/v1/case/update",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public CaseResponse updateCase(@Valid @RequestBody UpdateCaseRequest updateRequest) {

    return caseService.updateCase(updateRequest);
  }

  @DeleteMapping(path = "/api/v1/case/{caseNumber}")
  public CaseResponse deleteCase(@PathVariable String caseNumber) {
    return caseService.deleteCase(caseNumber);
  }

  @GetMapping(path = "/api/v1/case/caseNumber/{caseNumber}")
  public CaseResponse getCaseByCaseId(@PathVariable String caseNumber) {
    return caseService.getCaseByCaseId(caseNumber);
  }

  @GetMapping(path = "/api/v1/case/patientId/{patientId}")
  public CaseResponse getCaseByPatientId(String patientId) {
    return caseService.getCaseByPatientId(patientId);
  }
}
