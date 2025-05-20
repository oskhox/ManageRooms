package com.store.managerooms.controllers;

import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.RoomTypeRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomTypeController {

    private final RoomTypeRepo roomTypeRepo;

    public RoomTypeController(RoomTypeRepo roomTypeRepo) {
        this.roomTypeRepo = roomTypeRepo;
    }

    @RequestMapping("room/addbeds")
    public String addBeds(@RequestParam Long roomTypeId, @RequestParam int beds) {
        RoomType roomType = roomTypeRepo.findById(roomTypeId).get();
        roomType.setExtraBeds(beds);
        roomTypeRepo.save(roomType);
        return "Added " + beds + " beds " + " to this " + roomType.getName() + " room " + " with id: " + roomTypeId;
    }

}
