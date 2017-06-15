package edu.govst.dbms.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
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
    @Column(nullable = false)
    private String firstName;

    @NonNull
    @Column(nullable = false)
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
