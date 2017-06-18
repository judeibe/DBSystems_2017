package edu.govst.dbms.repository;

import edu.govst.dbms.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
