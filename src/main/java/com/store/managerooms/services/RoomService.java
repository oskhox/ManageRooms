package com.store.managerooms.services;

import com.store.managerooms.dtos.RoomDTO;
import com.store.managerooms.models.Room;

import java.util.List;

public interface RoomService {

    List<RoomDTO> getAllRooms();
    RoomDTO getRoomById(long id);
}
