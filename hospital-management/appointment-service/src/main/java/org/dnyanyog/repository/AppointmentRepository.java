package org.dnyanyog.repository;

import java.util.Optional;
import org.dnyanyog.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

  Optional<Appointment> findById(String appointmentId);

  Optional<Appointment> findByPatientId(String patientId);
}
