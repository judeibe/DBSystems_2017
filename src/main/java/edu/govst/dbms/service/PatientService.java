package edu.govst.dbms.service;

import edu.govst.dbms.model.Patient;

public interface PatientService {
    void create(Patient patient);

    boolean exists(Patient patient);
}
