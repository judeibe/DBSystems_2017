package edu.govst.dbms.service;

import edu.govst.dbms.model.Patient;
import edu.govst.dbms.model.PatientRecord;

public interface PatientService {
    Patient findById(long id);

    void create(Patient patient);

    boolean exists(Patient patient);

    void update(Patient patient);

    void delete(long id);

    Patient findPatientByFirstAndLastName(String firstName, String lastName);

    void addRecord(PatientRecord patientRecord);
}
