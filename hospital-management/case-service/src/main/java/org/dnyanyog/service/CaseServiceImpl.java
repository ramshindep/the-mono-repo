package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.dto.AddCaseRequest;
import org.dnyanyog.dto.CaseData;
import org.dnyanyog.dto.CaseResponse;
import org.dnyanyog.dto.UpdateCaseRequest;
import org.dnyanyog.entity.Cases;
import org.dnyanyog.enums.ErrorCode;
import org.dnyanyog.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseServiceImpl implements CaseService {
  @Autowired CaseResponse caseResponse;

  @Autowired CaseRepository caseRepo;

  @Autowired Cases caseTable;

  @Autowired CaseData caseData = new CaseData();

  public CaseResponse addCase(AddCaseRequest addRequest) {

    caseResponse = new CaseResponse();
    caseTable = new Cases();

    caseTable =
        Cases.getInstance()
            .setPatientName(addRequest.getPatientName())
            .setPatientId(addRequest.getPatientId())
            .setExaminationDate(addRequest.getExaminationDate())
            .setPrescription(addRequest.getPrescription())
            .setSymptoms(addRequest.getSymptoms())
            .setCaseStatus(Cases.ACTIVE)
            .build();

    caseTable = caseRepo.save(caseTable);

    caseResponse.setStatus(ErrorCode.CASE_ADDED_SUCCESS.getStatus());
    caseResponse.setMessage(ErrorCode.CASE_ADDED_SUCCESS.getMessage());

    caseData.setCaseNumber(caseTable.getCaseNumber());
    caseData.setPatientId(caseTable.getPatientId());
    caseData.setExaminationDate(caseTable.getExaminationDate());
    caseData.setPatientName(caseTable.getPatientName());
    caseData.setPrescription(caseTable.getPrescription());
    caseData.setSymptoms(caseTable.getSymptoms());
    caseData.setCaseStatus(caseTable.getCaseStatus());

    caseResponse.setCaseData(caseData);

    return caseResponse;
  }

  public CaseResponse updateCase(UpdateCaseRequest updateRequest) {
    Optional<Cases> cases = caseRepo.findById(updateRequest.getCaseNumber());
    if (cases.isPresent()) {
      try {
        caseTable = cases.get();

        if (updateRequest.getPatientId() != null) {
          caseTable.setPatientId(updateRequest.getPatientId());
        }
        if (updateRequest.getPatientName() != null) {
          caseTable.setPatientName(updateRequest.getPatientName());
        }
        if (updateRequest.getExaminationDate() != null) {
          caseTable.setExaminationDate(updateRequest.getExaminationDate());
        }
        if (updateRequest.getPrescription() != null) {
          caseTable.setPrescription(updateRequest.getPrescription());
        }
        if (updateRequest.getCaseStatus() != null) {
          caseTable.setCaseStatus(updateRequest.getCaseStatus());
        }

        caseTable = caseRepo.save(caseTable);

        caseResponse.setStatus(ErrorCode.CASE_UPDATE_SUCCESS.getStatus());
        caseResponse.setMessage(ErrorCode.CASE_UPDATE_SUCCESS.getMessage());

        caseData.setCaseNumber(caseTable.getCaseNumber());
        caseData.setPatientId(caseTable.getPatientId());
        caseData.setExaminationDate(caseTable.getExaminationDate());
        caseData.setPatientName(caseTable.getPatientName());
        caseData.setPrescription(caseTable.getPrescription());
        caseData.setSymptoms(caseTable.getSymptoms());
        caseData.setCaseStatus(caseTable.getCaseStatus());

        caseResponse.setCaseData(caseData);

      } catch (Exception e) {
        e.printStackTrace();
        caseResponse.setStatus(ErrorCode.CASE_UPDATE_FAILURE.getStatus());
        caseResponse.setMessage(ErrorCode.CASE_UPDATE_FAILURE.getMessage());
      }
    } else {
      caseResponse.setStatus(ErrorCode.CASE_NOT_FOUND.getStatus());
      caseResponse.setMessage(ErrorCode.CASE_NOT_FOUND.getMessage());
    }

    return caseResponse;
  }

  public CaseResponse deleteCase(String caseNumber) {

    Optional<Cases> cases = caseRepo.findById(caseNumber);
    if (cases.isPresent()) {
      caseTable = cases.get();
      caseTable.setCaseStatus(Cases.INACTIVE);
      caseRepo.save(caseTable);

      caseResponse.setStatus(ErrorCode.CASE_DELETE_SUCCESS.getStatus());
      caseResponse.setMessage(ErrorCode.CASE_DELETE_SUCCESS.getMessage());
      return (caseResponse);
    } else {
      caseResponse.setStatus(ErrorCode.CASE_NOT_FOUND.getStatus());
      caseResponse.setMessage(ErrorCode.CASE_NOT_FOUND.getMessage());
      return (caseResponse);
    }
  }

  public CaseResponse getCaseByCaseId(String caseNumber) {
    Optional<Cases> Optionalcases = caseRepo.findById(caseNumber);
    CaseResponse caseResponse = new CaseResponse();
    if (Optionalcases.isPresent()) {
      caseTable = Optionalcases.get();

      caseResponse.setStatus(ErrorCode.CASE_FOUND.getStatus());
      caseResponse.setMessage(ErrorCode.CASE_FOUND.getMessage());

      caseData.setCaseNumber(caseTable.getCaseNumber());
      caseData.setPatientId(caseTable.getPatientId());
      caseData.setPatientName(caseTable.getPatientName());
      caseData.setPrescription(caseTable.getPrescription());
      caseData.setExaminationDate(caseTable.getExaminationDate());
      caseData.setSymptoms(caseTable.getSymptoms());
      caseData.setCaseStatus(caseTable.getCaseStatus());

      caseResponse.setCaseData(caseData);
      return (caseResponse);
    } else {

      caseResponse.setStatus(ErrorCode.CASE_NOT_FOUND.getStatus());
      caseResponse.setMessage(ErrorCode.CASE_NOT_FOUND.getMessage());
      return (caseResponse);
    }
  }

  public CaseResponse getCaseByPatientId(String patientId) {
    Optional<Cases> Optionalcases = caseRepo.findByPatientId(patientId);
    CaseResponse caseResponse = new CaseResponse();
    if (Optionalcases.isPresent()) {
      caseTable = Optionalcases.get();

      caseResponse.setStatus(ErrorCode.CASE_FOUND.getStatus());
      caseResponse.setMessage(ErrorCode.CASE_FOUND.getMessage());

      caseData.setCaseNumber(caseTable.getCaseNumber());
      caseData.setPatientId(caseTable.getPatientId());
      caseData.setPatientName(caseTable.getPatientName());
      caseData.setPrescription(caseTable.getPrescription());
      caseData.setExaminationDate(caseTable.getExaminationDate());
      caseData.setSymptoms(caseTable.getSymptoms());
      caseData.setCaseStatus(caseTable.getCaseStatus());

      caseResponse.setCaseData(caseData);
      return (caseResponse);
    } else {

      caseResponse.setStatus(ErrorCode.CASE_NOT_FOUND.getStatus());
      caseResponse.setMessage(ErrorCode.CASE_NOT_FOUND.getMessage());
      return (caseResponse);
    }
  }
}
