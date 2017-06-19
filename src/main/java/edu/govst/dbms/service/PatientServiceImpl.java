package edu.govst.dbms.service;

import edu.govst.dbms.model.Patient;
import edu.govst.dbms.model.PatientRecord;
import edu.govst.dbms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient findById(long id) {
        return patientRepository.findOne(id);
    }

    @Override
    public void create(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public boolean exists(Patient patient) {
        return patientRepository.findOne(patient.getPatientId()) != null;
    }

    @Override
    public void update(Patient patient) {
        patientRepository.saveAndFlush(patient);
    }

    @Override
    public void delete(long id) {
        Patient patient = findById(id);
        patientRepository.delete(patient);
    }

    @Override
    public Patient findPatientByFirstAndLastName(String firstName, String lastName) {
        return patientRepository.findPatientByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void addRecord(PatientRecord patientRecord) {

    }


}
