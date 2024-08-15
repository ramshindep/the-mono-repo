package org.dnyanyog.service;

import org.dnyanyog.dto.AddAppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.dto.UpdateAppointmentRequest;
import org.springframework.stereotype.Component;

@Component
public interface AppointmentService {

  public AppointmentResponse addAppointment(AddAppointmentRequest addRequest);

  public AppointmentResponse updateAppointment(UpdateAppointmentRequest updateRequest);

  public AppointmentResponse deleteAppointment(String appointmentId);

  public AppointmentResponse getAppointmentByAppointmentId(String appointmentId);

  public AppointmentResponse getAppointmentByPatientId(String patientId);
}
