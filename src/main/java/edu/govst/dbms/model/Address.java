package edu.govst.dbms.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue
    public long addressId;
    public String addressLine1;
    public String addressLine2;
    public String city;
    public String state;
    public int zipCode;

    public Address() {
    }

    public Address(long addressId, String addressLine1, String addressLine2, String city, String state, int zipCode) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
