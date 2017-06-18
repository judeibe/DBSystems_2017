package edu.govst.dbms.repository;

import edu.govst.dbms.model.Address;
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
    private Address firstAddress, secondAddress, thirdAddress, fourthAddress;
    private long id;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private PatientRepository repository;

    @Before
    public void SetUp() throws Exception {
        firstAddress = new Address();
        firstAddress.setAddressLine1("123 Main St");
        firstAddress.setAddressLine2(null);
        firstAddress.setCity("Hershey");
        firstAddress.setState("WA");
        firstAddress.setZipCode(12345);

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
        firstPatient.setAddress(firstAddress);

        secondAddress = new Address();
        secondAddress.setAddressLine1("354 Main St");
        secondAddress.setAddressLine2(null);
        secondAddress.setCity("Hershey");
        secondAddress.setState("CA");
        secondAddress.setZipCode(12346);

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
        secondPatient.setAddress(secondAddress);

        thirdAddress = new Address();
        thirdAddress.setAddressLine1("456 Main St");
        thirdAddress.setAddressLine2(null);
        thirdAddress.setCity("Hershey");
        thirdAddress.setState("IL");
        thirdAddress.setZipCode(62345);

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
        thirdPatient.setAddress(thirdAddress);

        fourthAddress = new Address();
        fourthAddress.setAddressLine1("123 Main St");
        fourthAddress.setAddressLine2("Apt 3");
        fourthAddress.setCity("Hershey");
        fourthAddress.setState("NY");
        fourthAddress.setZipCode(12645);

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
        fourthPatient.setAddress(fourthAddress);


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