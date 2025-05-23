package com.store.managerooms.services;

import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;

import java.util.List;

public interface RoomService {

    List<RoomDto> getAllRooms();
    RoomDto getRoomById(long id);

    public Room findByRoomId(Long id);


    }
