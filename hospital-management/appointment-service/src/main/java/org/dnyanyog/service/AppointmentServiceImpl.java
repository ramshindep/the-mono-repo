package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.dto.AddAppointmentRequest;
import org.dnyanyog.dto.AppointmentData;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.dto.UpdateAppointmentRequest;
import org.dnyanyog.entity.Appointment;
import org.dnyanyog.enums.ErrorCode;
import org.dnyanyog.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

  Logger logger = LoggerFactory.getLogger(AppointmentService.class);

  @Autowired AppointmentResponse appointmentResponse;

  @Autowired AppointmentRepository appointmentRepo;

  @Autowired Appointment appointmentTable;

  public AppointmentResponse addAppointment(AddAppointmentRequest addRequest) {

    appointmentResponse = new AppointmentResponse();

    appointmentTable = new Appointment();
    appointmentTable =
        Appointment.getInstance()
            .setPatientName(addRequest.getPatientName())
            .setPatientId(addRequest.getPatientId())
            .setAppointmentTime(addRequest.getAppointmentTime())
            .setExaminationDate(addRequest.getExaminationDate())
            .setAppointmentStatus(Appointment.PENDING)
            .build();
    appointmentTable = appointmentRepo.save(appointmentTable);

    appointmentResponse.setStatus(ErrorCode.APPOINTMENT_ADD_SUCCESS.getStatus());
    appointmentResponse.setMessage(ErrorCode.APPOINTMENT_ADD_SUCCESS.getMessage());

    AppointmentData appointmentData = new AppointmentData();
    appointmentData.setAppointmentId(appointmentTable.getAppointmentId());
    appointmentData.setAppointmentTime(appointmentTable.getAppointmentTime());
    appointmentData.setPatientId(appointmentTable.getPatientId());
    appointmentData.setExaminationDate(appointmentTable.getExaminationDate());
    appointmentData.setAppointmentStatus(appointmentTable.getAppointmentStatus());
    appointmentData.setPatientName(appointmentTable.getPatientName());

    appointmentResponse.setAppointmentData(appointmentData);

    return (appointmentResponse);
  }

  public AppointmentResponse updateAppointment(UpdateAppointmentRequest updateRequest) {

    Optional<Appointment> appointment = appointmentRepo.findById(updateRequest.getAppointmentId());
    if (appointment.isPresent()) {
      try {
        appointmentTable = appointment.get();
        if (updateRequest.getPatientId() != null) {
          appointmentTable.setPatientId(updateRequest.getPatientId());
        }

        if (updateRequest.getPatientName() != null) {
          appointmentTable.setPatientName(updateRequest.getPatientName());
        }

        if (updateRequest.getAppointmentTime() != null) {
          appointmentTable.setAppointmentTime(updateRequest.getAppointmentTime());
        }

        if (updateRequest.getExaminationDate() != null) {
          appointmentTable.setExaminationDate(updateRequest.getExaminationDate());
        }

        if (updateRequest.getAppointmentStatus() != null) {
          appointmentTable.setAppointmentStatus(updateRequest.getAppointmentStatus());
        }
        appointmentTable = appointmentRepo.save(appointmentTable);

        appointmentResponse.setStatus(ErrorCode.APPOINTMENT_UPDATE_SUCCESS.getStatus());
        appointmentResponse.setMessage(ErrorCode.APPOINTMENT_UPDATE_SUCCESS.getMessage());

        AppointmentData appointmentData = new AppointmentData();
        appointmentData.setAppointmentId(appointmentTable.getAppointmentId());
        appointmentData.setAppointmentTime(appointmentTable.getAppointmentTime());
        appointmentData.setPatientId(appointmentTable.getPatientId());
        appointmentData.setExaminationDate(appointmentTable.getExaminationDate());
        appointmentData.setAppointmentStatus(appointmentTable.getAppointmentStatus());
        appointmentData.setPatientName(appointmentTable.getPatientName());

        appointmentResponse.setAppointmentData(appointmentData);

      } catch (Exception e) {
        e.printStackTrace();
        appointmentResponse.setStatus(ErrorCode.APPOINTMENT_UPDATE_FAILURE.getStatus());
        appointmentResponse.setMessage(ErrorCode.APPOINTMENT_UPDATE_FAILURE.getMessage());
      }

    } else {

      appointmentResponse.setStatus(ErrorCode.APPOINTMENT_NOT_FOUND.getStatus());
      appointmentResponse.setMessage(ErrorCode.APPOINTMENT_NOT_FOUND.getMessage());
    }

    return appointmentResponse;
  }

  public AppointmentResponse deleteAppointment(String appointmentId) {
    Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);
    if (appointment.isPresent()) {
      appointmentTable = appointment.get();
      appointmentTable.setAppointmentStatus(Appointment.CANCELLED);
      appointmentRepo.save(appointmentTable);

      appointmentResponse = new AppointmentResponse();
      appointmentResponse.setStatus(ErrorCode.APPOINTMENT_DELETE_SUCCESS.getStatus());
      appointmentResponse.setMessage(ErrorCode.APPOINTMENT_DELETE_SUCCESS.getMessage());

      return (appointmentResponse);
    } else {
      appointmentResponse.setStatus(ErrorCode.APPOINTMENT_NOT_FOUND.getStatus());
      appointmentResponse.setMessage(ErrorCode.APPOINTMENT_NOT_FOUND.getMessage());

      return appointmentResponse;
    }
  }

  public AppointmentResponse getAppointmentByAppointmentId(String appointmentId) {
    Optional<Appointment> appointment = appointmentRepo.findById(appointmentId);

    if (appointment.isPresent()) {
      appointmentTable = appointment.get();

      AppointmentResponse appointmentResponse = new AppointmentResponse();

      appointmentResponse.setStatus(ErrorCode.APPOINTMENT_FOUND_SUCCESS.getStatus());
      appointmentResponse.setMessage(ErrorCode.APPOINTMENT_FOUND_SUCCESS.getMessage());

      AppointmentData appointmentData = new AppointmentData();
      appointmentData.setAppointmentId(appointmentTable.getAppointmentId());
      appointmentData.setAppointmentTime(appointmentTable.getAppointmentTime());
      appointmentData.setPatientId(appointmentTable.getPatientId());
      appointmentData.setExaminationDate(appointmentTable.getExaminationDate());
      appointmentData.setAppointmentStatus(appointmentTable.getAppointmentStatus());
      appointmentData.setPatientName(appointmentTable.getPatientName());

      appointmentResponse.setAppointmentData(appointmentData);
      return (appointmentResponse);
    } else {
      AppointmentResponse appointmentResponse = new AppointmentResponse();
      appointmentResponse.setStatus(ErrorCode.APPOINTMENT_NOT_FOUND.getStatus());
      appointmentResponse.setMessage(ErrorCode.APPOINTMENT_NOT_FOUND.getMessage());

      return (appointmentResponse);
    }
  }

  public AppointmentResponse getAppointmentByPatientId(String patientId) {
    Optional<Appointment> appointment = appointmentRepo.findByPatientId(patientId);

    if (appointment.isPresent()) {
      appointmentTable = appointment.get();

      AppointmentResponse appointmentResponse = new AppointmentResponse();

      appointmentResponse.setStatus(ErrorCode.APPOINTMENT_FOUND_SUCCESS.getStatus());
      appointmentResponse.setMessage(ErrorCode.APPOINTMENT_FOUND_SUCCESS.getMessage());

      AppointmentData appointmentData = new AppointmentData();
      appointmentData.setAppointmentId(appointmentTable.getAppointmentId());
      appointmentData.setAppointmentTime(appointmentTable.getAppointmentTime());
      appointmentData.setPatientId(appointmentTable.getPatientId());
      appointmentData.setExaminationDate(appointmentTable.getExaminationDate());
      appointmentData.setAppointmentStatus(appointmentTable.getAppointmentStatus());
      appointmentData.setPatientName(appointmentTable.getPatientName());

      appointmentResponse.setAppointmentData(appointmentData);
      return (appointmentResponse);
    } else {
      AppointmentResponse appointmentResponse = new AppointmentResponse();
      appointmentResponse.setStatus(ErrorCode.APPOINTMENT_NOT_FOUND.getStatus());
      appointmentResponse.setMessage(ErrorCode.APPOINTMENT_NOT_FOUND.getMessage());

      return (appointmentResponse);
    }
  }
}
