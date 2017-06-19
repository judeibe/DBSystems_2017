package edu.govst.dbms.service;

import edu.govst.dbms.model.PatientRecord;
import edu.govst.dbms.repository.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientRecordServiceImpl implements PatientRecordService {

    private PatientRecordRepository patientRecordRepository;

    @Autowired
    public PatientRecordServiceImpl(PatientRecordRepository patientRecordRepository) {
        this.patientRecordRepository = patientRecordRepository;
    }

    @Override
    public void create(PatientRecord patientRecord) {
        patientRecordRepository.save(patientRecord);

    }
}
