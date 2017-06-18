package edu.govst.dbms.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue
    private long patientId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private long height;

    private long weight;

    private String nextOfKin;

    private String phone;

    private Boolean admitted;

    private String otherDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PatientRecord> patientRecord = new HashSet<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Admission> admissions = new HashSet<>();


    public Patient() {
    }

    public Patient(String firstName, String lastName, String gender, LocalDate birthDate, long height, long weight, String nextOfKin, String phone, Boolean admitted, String otherDetails, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.nextOfKin = nextOfKin;
        this.phone = phone;
        this.admitted = admitted;
        this.otherDetails = otherDetails;
        this.address = address;
    }
}
