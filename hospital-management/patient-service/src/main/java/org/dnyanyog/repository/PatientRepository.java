package org.dnyanyog.repository;

import java.util.Optional;
import org.dnyanyog.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

  Optional<Patient> findById(String patientId);

  Optional<Patient> findByPatientName(String patientName);
}
