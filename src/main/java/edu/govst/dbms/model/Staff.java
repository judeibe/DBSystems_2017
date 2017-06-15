package edu.govst.dbms.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Staff {

    @Id
    @GeneratedValue
    private long staffId;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String position;

    public Staff() {
    }

    public Staff(String firstName, String lastName, String position) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }
}
