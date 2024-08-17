package org.dnyanyog.controller;

import jakarta.validation.Valid;
import org.dnyanyog.dto.AddAppointmentRequest;
import org.dnyanyog.dto.AppointmentResponse;
import org.dnyanyog.dto.UpdateAppointmentRequest;
import org.dnyanyog.service.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

  @Autowired AppointmentServiceImpl appointmentService;

  @PostMapping(
      path = "/api/v1/appointment/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AppointmentResponse addAppointment(@Valid @RequestBody AddAppointmentRequest addRequest) {
    return appointmentService.addAppointment(addRequest);
  }

  @PostMapping(
      path = "/api/v1/appointment/update",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AppointmentResponse updateAppointment(
      @Valid @RequestBody UpdateAppointmentRequest UpdateRequest) {
    return appointmentService.updateAppointment(UpdateRequest);
  }

  @DeleteMapping(path = "/api/v1/appointment/{appointmentId}")
  public AppointmentResponse deleteAppointment(@PathVariable String appointmentId) {
    return appointmentService.deleteAppointment(appointmentId);
  }

  @GetMapping(path = "/api/v1/appointment/appointmentId/{appointmentId}")
  public AppointmentResponse searchAppointmentByAppointmentId(@PathVariable String appointmentId) {
    return appointmentService.getAppointmentByAppointmentId(appointmentId);
  }

  @GetMapping(path = "/api/v1/appointment/patientId/{patientId}")
  public AppointmentResponse searchAppointmentByPatientId(@PathVariable String patientId) {
    return appointmentService.getAppointmentByPatientId(patientId);
  }
}

