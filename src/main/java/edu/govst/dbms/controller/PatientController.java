package edu.govst.dbms.controller;

import edu.govst.dbms.model.Patient;
import edu.govst.dbms.model.PatientRecord;
import edu.govst.dbms.service.PatientRecordService;
import edu.govst.dbms.service.PatientService;
import edu.govst.dbms.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class PatientController {

    private PatientService patientService;
    private PatientRecordService patientRecordService;
    private StaffService staffService;

    @Autowired
    public PatientController(PatientService patientService, PatientRecordService patientRecordService, StaffService staffService) {
        this.patientService = patientService;
        this.patientRecordService = patientRecordService;
        this.staffService = staffService;
    }

    @RequestMapping(value = "/patient/new", method = RequestMethod.POST)
    public String save(Patient patient, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("There are errors! {}", bindingResult);
            return "patient/new";
        }

  /*      if (patientService.exists(patient)) {
            log.info("A patient with name " + patient.getFirstName() + " already exists");
            return new ModelAndView("/patient/new");
        }*/
        log.info("Creating new patient: {}", patient);

        patientService.create(patient);

        return "patients/";
    }

    @RequestMapping(value = "/patients/new", method = RequestMethod.GET)
    public ModelAndView create() {
        log.info("Showing patient add view");
        ModelAndView modelAndView = new ModelAndView("patients/new");
        modelAndView.addObject(new Patient());
        return modelAndView;
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Patient> update(@PathVariable long id, @RequestBody Patient patient) {
        log.info("Updating patient: {}", patient);
        Patient currentPatient = patientService.findById(id);

        if (isNull(id, currentPatient)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        currentPatient = patient;

        patientService.update(patient);
        return new ResponseEntity<>(currentPatient, HttpStatus.OK);
    }

    private boolean isNull(@PathVariable long id, Patient currentPatient) {
        if (currentPatient == null) {
            log.info("Patient with id {} not found", id);
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Deleting patient with id: {}", id);
        Patient patient = patientService.findById(id);

        if (isNull(id, patient)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable long id) {
        log.info("Getting patient with id: {}", id);
        ModelAndView modelAndView = new ModelAndView("patient/list", "patient", patientService.findById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/patient/{id}/addrecord", method = RequestMethod.GET)
    public ModelAndView createRecord(@PathVariable long id) {
        log.info("Creating Record for patient with id {}", id);
        ModelAndView modelAndView = new ModelAndView("patient/addrecord");
        modelAndView.addObject(new PatientRecord());
        modelAndView.addObject("allStaff", staffService.findAll());
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @RequestMapping(value = "/patient/{id}/addrecord", method = RequestMethod.POST)
    public String saveRecord(@PathVariable long id, PatientRecord patientRecord, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("There are errors! {}", bindingResult);
            return ("/patient/" + id + "/addrecord");
        }

        log.info("Creating new record: {}", patientRecord);

        Patient patient = patientService.findById(id);
        log.info("{}", patient);
        patientRecord.setPatient(patient);
        patientRecordService.create(patientRecord);

        return ("patient/" + id);
    }
}
