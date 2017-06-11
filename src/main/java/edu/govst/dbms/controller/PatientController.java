package edu.govst.dbms.controller;

import edu.govst.dbms.model.Patient;
import edu.govst.dbms.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Slf4j
@RequestMapping("/patients")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Patient patient, UriComponentsBuilder ucBuilder) {
        log.info("Creating new patient: {}", patient);

        if (patientService.exists(patient)) {
            log.info("A patient with name " + patient.getFirstName() + " already exists");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        patientService.create(patient);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/patient/{id}").buildAndExpand(patient.getPatientId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
