package edu.govst.dbms.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = {"patientRecords", "admissions"})
@ToString(exclude = {"patientRecords", "admissions"})
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue
    private long patientId;

    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    private String gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private long height;

    private long weight;

    private String nextOfKin;

    private String phone;

    private Boolean admitted;

    private String otherDetails;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private int zipCode;

    @OneToMany(mappedBy = "patient")
    private Set<PatientRecord> patientRecords = new HashSet<>();

    @OneToMany(mappedBy = "patient")
    private Set<Admission> admissions = new HashSet<>();


    public Patient() {
    }

    public Patient(String firstName, String middleName, String lastName, String gender, LocalDate birthDate, long height, long weight, String nextOfKin, String phone, Boolean admitted, String otherDetails, String addressLine1, String addressLine2, String city, String state, int zipCode) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.nextOfKin = nextOfKin;
        this.phone = phone;
        this.admitted = admitted;
        this.otherDetails = otherDetails;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
