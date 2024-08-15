package org.dnyanyog.controller;

import jakarta.validation.Valid;
import org.dnyanyog.dto.AddPatientRequest;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.dto.UpdatePatientRequest;
import org.dnyanyog.service.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

  @Autowired PatientServiceImpl patientService;

  @PostMapping(
      path = "/api/v1/patient/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public PatientResponse addPatient(@Valid @RequestBody AddPatientRequest addRequest) {

    return patientService.addPatient(addRequest);
  }

  @PostMapping(
      path = "/api/v1/patient/update",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public PatientResponse updatePatient(@Valid @RequestBody UpdatePatientRequest updateRequest) {
    return patientService.updatePatient(updateRequest);
  }

  @DeleteMapping(path = "/api/v1/patient/{patientId}")
  public PatientResponse deletePatient(@Valid @PathVariable String patientId) {

    return patientService.deletePatient(patientId);
  }

  @GetMapping(path = "/api/v1/patient/{patientId}")
  public PatientResponse getPatient(@PathVariable String patientId) {
    return patientService.getPatientById(patientId);
  }

  @GetMapping(path = "/api/v1/patient/patientName/{patientName}")
  public PatientResponse getPatientByPatientName(@PathVariable String patientName) {
    return patientService.getPatientByPatientName(patientName);
  }
}
