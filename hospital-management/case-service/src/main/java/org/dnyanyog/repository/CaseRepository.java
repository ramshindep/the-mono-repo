package org.dnyanyog.repository;

import java.util.Optional;
import org.dnyanyog.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Cases, String> {

  Optional<Cases> findById(String caseNumber);

  Optional<Cases> findByPatientId(String patientId);
}
