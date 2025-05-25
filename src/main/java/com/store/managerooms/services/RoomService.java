package com.store.managerooms.services;

import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<RoomDto> getAllRooms();
    public Room findByRoomId(Long id);
    List<Room>getAvailableRooms(int peopleCount, LocalDate start, LocalDate end);
    String addBeds (Long roomTypeId,int beds);


    }
