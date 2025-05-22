package com.store.managerooms.repos;

import com.store.managerooms.models.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {
}
