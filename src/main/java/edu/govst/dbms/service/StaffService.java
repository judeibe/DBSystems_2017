package edu.govst.dbms.service;

import edu.govst.dbms.model.Staff;

import java.util.List;

public interface StaffService {
    void create(Staff staff);

    List<Staff> findAll();

    Staff findById(long id);
}
