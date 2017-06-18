package edu.govst.dbms.repository;

import edu.govst.dbms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Transactional
    java.util.Optional<Patient> findByPatientId(long patientId);
}

