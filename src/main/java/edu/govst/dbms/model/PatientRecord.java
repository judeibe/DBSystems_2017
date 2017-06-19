package edu.govst.dbms.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "patient_record")
public class PatientRecord implements Serializable {

    public String notes;
    @Id
    @GeneratedValue
    long patientRecordId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;


    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;

    public PatientRecord() {
    }


    public PatientRecord(Patient patient, Staff staff, String notes) {
        this.patient = patient;
        this.staff = staff;
        this.notes = notes;
    }
}
