package edu.govst.dbms.service;

import edu.govst.dbms.model.Patient;
import edu.govst.dbms.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public void create(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public boolean exists(Patient patient) {
        return patientRepository.findOne(patient.getPatientId()) != null;
    }
}
