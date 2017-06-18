package edu.govst.dbms.repository;

import edu.govst.dbms.model.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {
}
