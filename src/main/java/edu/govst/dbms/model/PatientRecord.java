package edu.govst.dbms.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
public class PatientRecord implements Serializable {

    public String notes;
    @Id
    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;
    @Id
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
