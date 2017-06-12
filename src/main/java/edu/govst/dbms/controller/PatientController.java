package edu.govst.dbms.controller;

import edu.govst.dbms.model.Patient;
import edu.govst.dbms.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@Slf4j
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
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

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> update(@PathVariable long id, @RequestBody Patient patient) {
        log.info("Updating patient: {}", patient);
        Patient currentPatient = patientService.findById(id);

        if (currentPatient == null) {
            log.info("Patient with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentPatient = patient;

        patientService.update(patient);
        return new ResponseEntity<>(currentPatient, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Deleting patient with id: {}", id);
        Patient patient = patientService.findById(id);

        if (patient == null) {
            log.info("Patient with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Patient> get(@PathVariable long id) {
        log.info("Getting patient with id: {}, id");
        Patient patient = patientService.findById(id);

        if (patient == null) {
            log.info("Patient with id {} not found, id");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
