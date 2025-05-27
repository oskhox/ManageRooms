package com.store.managerooms.services;

import com.store.managerooms.dtos.AddBedsDto;
import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<RoomDto> getAllRooms();
    RoomDto findByRoomId(Long id);
    List<RoomDto>getAvailableRooms(int peopleCount, LocalDate start, LocalDate end);
    void addBeds (AddBedsDto addBeds);
    }
