package com.store.managerooms.services;

import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<RoomDto> getAllRooms();
    Room findByRoomId(Long id);
    String addBeds (Long roomTypeId,int beds);
    List<RoomDto> getAvailableRooms(int peopleCount, LocalDate start, LocalDate end);
    RoomDto convertToDto(Room room);


    }