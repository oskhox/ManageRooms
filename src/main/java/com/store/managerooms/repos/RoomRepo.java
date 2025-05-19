package com.store.managerooms.repos;

import com.store.managerooms.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {

}
