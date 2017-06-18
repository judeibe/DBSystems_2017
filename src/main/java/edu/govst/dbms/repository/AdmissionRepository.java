package edu.govst.dbms.repository;

import edu.govst.dbms.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
}
