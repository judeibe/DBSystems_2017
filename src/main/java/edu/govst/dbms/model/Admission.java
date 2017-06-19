package edu.govst.dbms.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "admission")
public class Admission implements Serializable {

    @Id
    @GeneratedValue
    private long admissionId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "roomNumber")
    private Room room;

    private LocalDate admitDate;

    private LocalDate dischargeDate;

    public Admission() {
    }

    public Admission(Patient patient, Staff staff, Room room, LocalDate admitDate, LocalDate dischargeDate) {
        this.patient = patient;
        this.staff = staff;
        this.room = room;
        this.admitDate = admitDate;
        this.dischargeDate = dischargeDate;
    }
}
