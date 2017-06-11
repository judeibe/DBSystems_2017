package edu.govst.dbms.service;

import edu.govst.dbms.model.Patient;

public interface PatientService {
    Patient findById(long id);

    void create(Patient patient);

    boolean exists(Patient patient);

    void update(Patient patient);
}
