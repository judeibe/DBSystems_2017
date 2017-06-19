package edu.govst.dbms.repository;

import edu.govst.dbms.model.Patient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PatientRepositoryTest {

    private Patient firstPatient, secondPatient, thirdPatient, fourthPatient;
    private long id;

    @PersistenceContext
    private EntityManager em;
    @Autowired
    private PatientRepository repository;

    @Before
    public void SetUp() throws Exception {


        firstPatient = new Patient();
        firstPatient.setFirstName("John");
        firstPatient.setLastName("Adams");
        firstPatient.setGender("M");
        firstPatient.setBirthDate(LocalDate.of(2005, 8, 14));
        firstPatient.setHeight(66);
        firstPatient.setWeight(145);
        firstPatient.setNextOfKin("Tom Sawyer");
        firstPatient.setPhone("123-456-789");
        firstPatient.setAdmitted(false);
        firstPatient.setOtherDetails(null);
        firstPatient.setAddressLine1("123 Main St");
        firstPatient.setAddressLine2(null);
        firstPatient.setCity("Hershey");
        firstPatient.setState("WA");
        firstPatient.setZipCode(12345);

        secondPatient = new Patient();
        secondPatient.setFirstName("Tom");
        secondPatient.setLastName("Sawyer");
        secondPatient.setGender("M");
        secondPatient.setBirthDate(LocalDate.of(1987, 3, 14));
        secondPatient.setHeight(66);
        secondPatient.setWeight(145);
        secondPatient.setNextOfKin("Tom Sawyer");
        secondPatient.setPhone("123-456-789");
        secondPatient.setAdmitted(false);
        secondPatient.setOtherDetails(null);
        secondPatient.setAddressLine1("354 Main St");
        secondPatient.setAddressLine2(null);
        secondPatient.setCity("Hershey");
        secondPatient.setState("CA");
        secondPatient.setZipCode(12346);


        thirdPatient = new Patient();
        thirdPatient.setFirstName("Ryan");
        thirdPatient.setLastName("Hardy");
        thirdPatient.setGender("M");
        thirdPatient.setBirthDate(LocalDate.of(1988, 12, 14));
        thirdPatient.setHeight(66);
        thirdPatient.setWeight(145);
        thirdPatient.setNextOfKin("Tom Sawyer");
        thirdPatient.setPhone("123-456-789");
        thirdPatient.setAdmitted(false);
        thirdPatient.setOtherDetails(null);
        thirdPatient.setAddressLine1("456 Main St");
        thirdPatient.setAddressLine2(null);
        thirdPatient.setCity("Hershey");
        thirdPatient.setState("IL");
        thirdPatient.setZipCode(62345);


        fourthPatient = new Patient();
        fourthPatient.setFirstName("Jessica");
        fourthPatient.setLastName("Lane");
        fourthPatient.setGender("F");
        fourthPatient.setBirthDate(LocalDate.of(1988, 12, 14));
        fourthPatient.setHeight(66);
        fourthPatient.setWeight(145);
        fourthPatient.setNextOfKin("Tom Sawyer");
        fourthPatient.setPhone("123-456-789");
        fourthPatient.setAdmitted(false);
        fourthPatient.setOtherDetails(null);
        fourthPatient.setAddressLine1("123 Main St");
        fourthPatient.setAddressLine2("Apt 3");
        fourthPatient.setCity("Hershey");
        fourthPatient.setState("NY");
        fourthPatient.setZipCode(12645);

    }

    @Test
    public void testCreation() throws Exception {
        //noinspection JpaQlInspection
        Query countQuery = em.createQuery("select count(u) from Patient u");
        Long before = (Long) countQuery.getSingleResult();

        flushTestPatients();

        assertThat((Long) countQuery.getSingleResult()).isEqualTo(before + 4L);

    }

    private void flushTestPatients() {

        firstPatient = repository.save(firstPatient);
        secondPatient = repository.save(secondPatient);
        thirdPatient = repository.save(thirdPatient);
        fourthPatient = repository.save(fourthPatient);

        repository.flush();

        id = firstPatient.getPatientId();

        assertThat(id).isNotNull();
        assertThat(secondPatient.getPatientId()).isNotNull();
        assertThat(thirdPatient.getPatientId()).isNotNull();
        assertThat(fourthPatient.getPatientId()).isNotNull();

        assertThat(repository.exists(id)).isTrue();
        assertThat(repository.exists(secondPatient.getPatientId())).isTrue();
        assertThat(repository.exists(thirdPatient.getPatientId())).isTrue();
        assertThat(repository.exists(fourthPatient.getPatientId())).isTrue();
    }

}