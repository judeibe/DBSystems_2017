package edu.govst.dbms.service;

import edu.govst.dbms.GroupProjectApplicationTests;
import edu.govst.dbms.controller.PatientController;
import edu.govst.dbms.model.Address;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Test
    public void findById_KnownPatientId_ShouldReturnPatient() throws Exception {
        Address address = new Address();
        address.setAddressId(1);
        address.setAddressLine1("123 Main St");
        address.setAddressLine2(null);
        address.setCity("Hershey");
        address.setState("WA");
        address.setZipCode(12345);
        Patient patient = new Patient();
        patient.setPatientId(1);
        patient.setFirstName("John");
        patient.setLastName("Adams");
        patient.setGender("M");
        patient.setBirthDate(LocalDate.of(1988, 12, 14));
        patient.setHeight(66);
        patient.setWeight(145);
        patient.setNextOfKin("Tom Sawyer");
        patient.setPhone("123-456-789");
        patient.setAdmitted(false);
        patient.setOtherDetails(null);
        patient.setAddress(address);
        when(patientService.findById(patient.getPatientId())).thenReturn(patient);
        mockMvc.perform(
                get("/patient/{id}", patient.getPatientId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.patientId").value("1"))
                .andExpect(jsonPath("$.lastName").value("Adams"));
        verify(patientService, times(1)).findById(patient.getPatientId());
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void findById_UnknownPatientId_ShoudReturnStatusIsNotFound() throws Exception {
        Patient patient = new Patient();
        when(patientService.findById(patient.getPatientId())).thenReturn(null);
        mockMvc.perform(
                get("/patient/{id}", patient.getPatientId()))
                .andExpect(status().isNotFound());
        verify(patientService, times(1)).findById(patient.getPatientId());
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void create_NewPatient_ShouldCreateNewPatient() throws Exception {
        Address address = new Address();
        address.setAddressId(1);
        address.setAddressLine1("123 Main St");
        address.setAddressLine2(null);
        address.setCity("Hershey");
        address.setState("WA");
        address.setZipCode(12345);
        Patient patient = new Patient();
        patient.setPatientId(1);
        patient.setFirstName("John");
        patient.setLastName("Adams");
        patient.setGender("M");
        patient.setBirthDate(LocalDate.of(1988, 12, 14));
        patient.setHeight(66);
        patient.setWeight(145);
        patient.setNextOfKin("Tom Sawyer");
        patient.setPhone("123-456-789");
        patient.setAdmitted(false);
        patient.setOtherDetails(null);
        patient.setAddress(address);
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
        Address address = new Address();
        address.setAddressId(1);
        address.setAddressLine1("123 Main St");
        address.setAddressLine2(null);
        address.setCity("Hershey");
        address.setState("WA");
        address.setZipCode(12345);
        Patient patient = new Patient();
        patient.setPatientId(1);
        patient.setFirstName("John");
        patient.setLastName("Adams");
        patient.setGender("M");
        patient.setBirthDate(LocalDate.of(1988, 12, 14));
        patient.setHeight(66);
        patient.setWeight(145);
        patient.setNextOfKin("Tom Sawyer");
        patient.setPhone("123-456-789");
        patient.setAdmitted(false);
        patient.setOtherDetails(null);
        patient.setAddress(address);
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

    @Test
    public void delete_KnownPatientId_ShouldDeletePatient() throws Exception {
        Address address = new Address();
        address.setAddressId(1);
        address.setAddressLine1("123 Main St");
        address.setAddressLine2(null);
        address.setCity("Hershey");
        address.setState("WA");
        address.setZipCode(12345);
        Patient patient = new Patient();
        patient.setPatientId(1);
        patient.setFirstName("John");
        patient.setLastName("Adams");
        patient.setGender("M");
        patient.setBirthDate(LocalDate.of(1988, 12, 14));
        patient.setHeight(66);
        patient.setWeight(145);
        patient.setNextOfKin("Tom Sawyer");
        patient.setPhone("123-456-789");
        patient.setAdmitted(false);
        patient.setOtherDetails(null);
        patient.setAddress(address);
        when(patientService.findById(patient.getPatientId())).thenReturn(patient);
        doNothing().when(patientService).delete(patient.getPatientId());
        mockMvc.perform(
                delete("/patient/{id}", patient.getPatientId()))
                .andExpect(status().isOk());
        verify(patientService, times(1)).findById(patient.getPatientId());
        verify(patientService, times(1)).delete(patient.getPatientId());
        verifyNoMoreInteractions(patientService);
    }

    @Test
    public void delete_UnknownPatientId_ShouldReturnStatusIsNotFound() throws Exception {
        Patient patient = new Patient();
        when(patientService.findById(patient.getPatientId())).thenReturn(null);
        mockMvc.perform(
                delete("/patient/{id}", patient.getPatientId()))
                .andExpect(status().isNotFound());
        verify(patientService, times(1)).findById(patient.getPatientId());
        verifyNoMoreInteractions(patientService);
    }

}