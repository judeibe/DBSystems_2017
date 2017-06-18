package edu.govst.dbms.repository;

import edu.govst.dbms.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
