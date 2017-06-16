package edu.govst.dbms.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Room {

    @Id
    @GeneratedValue
    private long roomNumber;

    private int freeBeds;

    @OneToMany(mappedBy = "room")
    private Set<Admission> admissions = new HashSet<>();

    public Room() {
    }

    public Room(int freeBeds) {
        this.freeBeds = freeBeds;
    }
}
