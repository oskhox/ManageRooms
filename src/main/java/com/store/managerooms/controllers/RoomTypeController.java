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
        int availableBeds = roomType.getExtraBedsAvailable();

        if (availableBeds != 0 && availableBeds <= beds) {
            roomType.setBedCount(roomType.getBedCount() + beds);
            roomType.setExtraBedsAvailable(availableBeds - beds);
            roomTypeRepo.save(roomType);
            return "Added " + beds + " beds " + " to " + roomType.getName() + " with id: " + roomTypeId;
        }
        else if(roomType.getName().equals("Single room")){
                return "This is a Single room, you cant add any extra beds to this room";
            }
        else {
            return "There is " + roomType.getExtraBedsAvailable() + " extra beds available";
        }
    }

}