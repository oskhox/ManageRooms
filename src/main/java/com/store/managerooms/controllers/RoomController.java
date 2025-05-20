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
                              @RequestParam String name,
                              @RequestParam int beds,
                              @RequestParam boolean extraBeds){

        if(extraBeds){
            Room doubleRoom = new Room(roomNumber,new RoomType(name,beds,0,extraBeds));
            roomRepo.save(doubleRoom);
        }else{
            Room room = new Room(roomNumber, new RoomType(name,beds));
            roomRepo.save(room);
        }
        return roomRepo.findAll();
    }
}
