package edu.govst.dbms.model;

import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Value
public class Patient {
    public String firstName;
    public String middleName;
    public String lastName;
    public LocalDate birthDate;
}
