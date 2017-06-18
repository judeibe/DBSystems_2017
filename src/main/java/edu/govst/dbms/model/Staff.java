package edu.govst.dbms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue
    private long staffId;

    private String firstName;

    private String lastName;

    private String position;

    @OneToMany(mappedBy = "staff")
    private Set<PatientRecord> patientRecords = new HashSet<>();

    @OneToMany(mappedBy = "staff")
    private Set<Admission> admissions = new HashSet<>();

    public Staff() {
    }

    public Staff(String firstName, String lastName, String position) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

}
