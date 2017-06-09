package edu.govst.dbms;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupProjectApplicationTests {

	private MockMvc mockMvc;

	@Mock
	private PatientService patientService;

	@InjectMocks
	private PatientController patientController;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders
				.standaloneSetup(patientController)
				.build();
	}


	@Test
	public void contextLoads() {
	}

	@Test
	public void test_add_patient_success() throws Exception {
		Patient patient = new Patient("Tom", "L", "Sawyer", LocalDate.of(1988,12,14));
		when(patientService.exits(patient).thenReturn(false));
		doNothing().when(patientService).create(patient);
		mockMvc.perform(post("/patients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(patient)))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", containsString("/patients/0")));
		verify(patientService, times(1)).exists(patient);
		verify(patientService, times(1)).create(patient);
		verifyNoMoreInteractions(patientService);
	}



}
