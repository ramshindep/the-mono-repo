package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.dto.AddPatientRequest;
import org.dnyanyog.dto.PatientData;
import org.dnyanyog.dto.PatientResponse;
import org.dnyanyog.dto.UpdatePatientRequest;
import org.dnyanyog.entity.Patient;
import org.dnyanyog.enums.ErrorCode;
import org.dnyanyog.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

  Logger logger = LoggerFactory.getLogger(PatientService.class);

  @Autowired PatientResponse patientResponse;

  @Autowired PatientRepository patientRepo;

  @Autowired Patient patientTable;

  public PatientResponse addPatient(AddPatientRequest addRequest) {

    patientTable = new Patient();

    patientTable =
        Patient.getInstance()
            .setPatientName(addRequest.getPatientName())
            .setPatientNameMarathi(addRequest.getPatientNameMarathi())
            .setMobileNumber(addRequest.getMobileNumber())
            .setFirstExaminationDate(addRequest.getFirstExaminationDate())
            .setBirthDate(addRequest.getBirthDate())
            .setGender(addRequest.getGender())
            .setAddress(addRequest.getAddress())
            .setStatus(Patient.ACTIVE)
            .build();

    patientTable = patientRepo.save(patientTable);

    patientResponse.setStatus(ErrorCode.PATIENT_ADD_SUCCESS.getStatus());
    patientResponse.setMessage(ErrorCode.PATIENT_ADD_SUCCESS.getMessage());

    PatientData patientData = new PatientData();
    patientData.setPatientId(patientTable.getPatientId());
    patientData.setPatientName(patientTable.getPatientName());
    patientData.setPatientNameMarathi(patientTable.getPatientNameMarathi());
    patientData.setGender(patientTable.getGender());
    patientData.setFirstExaminationDate(patientTable.getFirstExaminationDate());
    patientData.setMobileNumber(patientTable.getMobileNumber());
    patientData.setBirthDate(patientTable.getBirthDate());
    patientData.setAddress(patientTable.getAddress());
    patientData.setStatus(patientTable.getStatus());

    patientResponse.setPatientData(patientData);

    return patientResponse;
  }

  public PatientResponse updatePatient(UpdatePatientRequest updateRequest) {

    Optional<Patient> patient = patientRepo.findById(updateRequest.getPatientId());
    if (patient.isPresent()) {
      try {
        patientTable = patient.get();

        if (updateRequest.getPatientName() != null) {
          patientTable.setPatientName(updateRequest.getPatientName());
        }

        if (updateRequest.getPatientNameMarathi() != null) {
          patientTable.setPatientNameMarathi(updateRequest.getPatientNameMarathi());
        }

        if (updateRequest.getGender() != null) {
          patientTable.setGender(updateRequest.getGender());
        }

        if (updateRequest.getFirstExaminationDate() != null) {
          patientTable.setFirstExaminationDate(updateRequest.getFirstExaminationDate());
        }

        if (updateRequest.getMobileNumber() != null) {
          patientTable.setMobileNumber(updateRequest.getMobileNumber());
        }

        if (updateRequest.getBirthDate() != null) {
          patientTable.setBirthDate(updateRequest.getBirthDate());
        }

        if (updateRequest.getAddress() != null) {
          patientTable.setAddress(updateRequest.getAddress());
        }

        if (updateRequest.getStatus() != null) {
          patientTable.setStatus(updateRequest.getStatus());
        }

        patientTable = patientRepo.save(patientTable);
        patientResponse.setStatus(ErrorCode.PATIENT_UPDATE_SUCCESS.getStatus());
        patientResponse.setMessage(ErrorCode.PATIENT_UPDATE_SUCCESS.getMessage());

        patientResponse.setPatientId(patientTable.getPatientId());
        patientResponse.getPatientData().setPatientName(patientTable.getPatientName());
        patientResponse
            .getPatientData()
            .setPatientNameMarathi(patientTable.getPatientNameMarathi());
        patientResponse.getPatientData().setGender(patientTable.getGender());
        patientResponse
            .getPatientData()
            .setFirstExaminationDate(patientTable.getFirstExaminationDate());
        patientResponse.getPatientData().setMobileNumber(patientTable.getMobileNumber());
        patientResponse.getPatientData().setBirthDate(patientTable.getBirthDate());
        patientResponse.getPatientData().setAddress(patientTable.getAddress());
        patientResponse.getPatientData().setStatus(patientTable.getStatus());

      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      patientResponse.setStatus(ErrorCode.PATIENT_NOTFOUND.getStatus());
      patientResponse.setMessage(ErrorCode.PATIENT_NOTFOUND.getMessage());
    }
    return patientResponse;
  }

  public PatientResponse deletePatient(String patientId) {

    Optional<Patient> patient = patientRepo.findById(patientId);
    if (patient.isPresent()) {
      patientTable = patient.get();
      patientTable.setStatus(Patient.INACTIVE);
      patientRepo.save(patientTable);

      patientResponse = new PatientResponse();
      patientResponse.setStatus(ErrorCode.PATIENT_DELETE_SUCCESS.getStatus());
      patientResponse.setMessage(ErrorCode.PATIENT_DELETE_SUCCESS.getMessage());

      return (patientResponse);
    } else {
      patientResponse = new PatientResponse();
      patientResponse.setStatus(ErrorCode.PATIENT_NOTFOUND.getStatus());
      patientResponse.setMessage(ErrorCode.PATIENT_NOTFOUND.getMessage());
      return (patientResponse);
    }
  }

  public PatientResponse getPatientById(String patientId) {
    Optional<Patient> optionalPatient = patientRepo.findById(patientId);

    if (optionalPatient.isPresent()) {
      Patient patient = optionalPatient.get();

      PatientResponse patientResponse = new PatientResponse();
      patientResponse.setStatus(ErrorCode.PATIENT_FOUND.getStatus());
      patientResponse.setMessage(ErrorCode.PATIENT_FOUND.getMessage());

      PatientData patientData = new PatientData();
      patientData.setPatientId(patient.getPatientId());
      patientData.setPatientName(patient.getPatientName());
      patientData.setPatientNameMarathi(patient.getPatientNameMarathi());
      patientData.setGender(patient.getGender());
      patientData.setAddress(patient.getAddress());
      patientData.setBirthDate(patient.getBirthDate());
      patientData.setFirstExaminationDate(patient.getFirstExaminationDate());
      patientData.setMobileNumber(patient.getMobileNumber());
      patientData.setStatus(patient.getStatus());

      patientResponse.setPatientData(patientData);
      return (patientResponse);

    } else {
      PatientResponse patientResponse = new PatientResponse();
      patientResponse.setStatus(ErrorCode.PATIENT_NOTFOUND.getStatus());
      patientResponse.setMessage(ErrorCode.PATIENT_NOTFOUND.getMessage());

      return (patientResponse);
    }
  }

  public PatientResponse getPatientByPatientName(String patientName) {

    Optional<Patient> OptionalPatient = patientRepo.findByPatientName(patientName);

    System.out.print("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$" + OptionalPatient);
    if (OptionalPatient.isPresent()) {
      Patient patient = OptionalPatient.get();

      PatientResponse patientResponse = new PatientResponse();
      patientResponse.setStatus(ErrorCode.PATIENT_FOUND.getStatus());
      patientResponse.setMessage(ErrorCode.PATIENT_FOUND.getMessage());

      PatientData patientData = new PatientData();
      patientData.setPatientId(patient.getPatientId());
      patientData.setPatientName(patient.getPatientName());
      patientData.setPatientNameMarathi(patient.getPatientNameMarathi());
      patientData.setGender(patient.getGender());
      patientData.setAddress(patient.getAddress());
      patientData.setBirthDate(patient.getBirthDate());
      patientData.setFirstExaminationDate(patient.getFirstExaminationDate());
      patientData.setMobileNumber(patient.getMobileNumber());
      patientData.setStatus(patient.getStatus());

      patientResponse.setPatientData(patientData);
      return (patientResponse);

    } else {

      PatientResponse patientResponse = new PatientResponse();
      patientResponse.setStatus(ErrorCode.PATIENT_NOTFOUND.getStatus());
      patientResponse.setMessage(ErrorCode.PATIENT_NOTFOUND.getMessage());

      return (patientResponse);
    }
  }
}
