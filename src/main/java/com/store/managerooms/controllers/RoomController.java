package com.store.managerooms.controllers;

import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.RoomRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {

    private final RoomRepo roomRepo;

    public RoomController(RoomRepo roomRepo) {
        this.roomRepo = roomRepo;
    }

    @RequestMapping("rooms")
    public List<Room> getAllRooms(){
        return roomRepo.findAll();
    }

    @RequestMapping("rooms/add")
    public List<Room> addRoom(@RequestParam int roomNumber,
                              @RequestParam int beds,
                              @RequestParam int extraBedsAvailable){

        if(extraBedsAvailable == 1 && beds == 2 ) {
            Room doubleRoom = new Room(roomNumber, new RoomType("Double room", beds, extraBedsAvailable));
            roomRepo.save(doubleRoom);
        }
        else if(extraBedsAvailable == 2 && beds == 2 ) {
            Room bigDoubleRoom = new Room(roomNumber, new RoomType("Big double room", beds, extraBedsAvailable));
            roomRepo.save(bigDoubleRoom);
            }
        else if(beds == 1 && extraBedsAvailable == 0) {
            Room room = new Room(roomNumber, new RoomType("Single room",beds));
            roomRepo.save(room);
        }
        return roomRepo.findAll();
    }
}
