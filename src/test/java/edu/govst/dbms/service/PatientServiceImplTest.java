package edu.govst.dbms.service;

import edu.govst.dbms.GroupProjectApplicationTests;
import edu.govst.dbms.controller.PatientController;
import edu.govst.dbms.model.Patient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientServiceImplTest extends GroupProjectApplicationTests {

    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(patientController)
                .build();
    }

    //create user tests
    @Test
    public void create_NewPatient_ShouldCreateNewPatient() throws Exception {
        Patient patient = new Patient(1, "Tom", "L", "Sawyer", LocalDate.of(1988, 12, 14));
        when(patientService.exists(patient)).thenReturn(false);
        doNothing().when(patientService).create(patient);
        mockMvc.perform(
                post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patient)))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", containsString("/patient/1")));
        verify(patientService, times(1)).exists(patient);
        verify(patientService, times(1)).create(patient);
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void create_ExistingPatient_ShouldReturnStatusIsConflict() throws Exception {
        Patient patient = new Patient();
        when(patientService.exists(patient)).thenReturn(true);
        mockMvc.perform(
                post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patient)))
                .andExpect(status().isConflict());
        verify(patientService, times(1)).exists(patient);
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void update_UpdatedPatient_ShouldUpdateExistingPatient() throws Exception {
        Patient patient = new Patient(2, "John", "T", "Adams", LocalDate.of(1988, 12, 14));
        when(patientService.findById(patient.getPatientId())).thenReturn(patient);
        doNothing().when(patientService).update(patient);
        mockMvc.perform(
                put("/patient/{id}", patient.getPatientId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patient)))
                .andExpect(status().isOk());
        verify(patientService, times(1)).findById(patient.getPatientId());
        verify(patientService, times(1)).update(patient);
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void update_UnknownPatient_ShouldReturnStatusIsNotFound() throws Exception {
        Patient patient = new Patient();
        when(patientService.findById(patient.getPatientId())).thenReturn(null);
        mockMvc.perform(
                put("/patient/{id}", patient.getPatientId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(patient)))
                .andExpect(status().isNotFound());
        verify(patientService, times(1)).findById(patient.getPatientId());
        verifyNoMoreInteractions(patientService);
    }

}