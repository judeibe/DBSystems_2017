package edu.govst.dbms.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Patient {
    public String firstName;
    public String middleName;
    public String lastName;
    public LocalDate birthDate;
}
