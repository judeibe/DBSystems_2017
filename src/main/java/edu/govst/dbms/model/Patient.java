package edu.govst.dbms.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue
    private long patientId;

    @NonNull
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
    private String lastName;

    private String gender;

    private LocalDate birthDate;

    private long height;

    private long weight;

    private String nextOfKin;

    private String phone;

    @NonNull
    private Boolean admitted;

    private String otherDetails;

    @ManyToOne
    private Address address;

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
