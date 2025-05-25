package com.store.managerooms.controllers;

import ch.qos.logback.core.model.Model;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.RoomTypeRepo;
import com.store.managerooms.services.RoomService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomService roomService;
//    private final RoomTypeRepo roomTypeRepo;

//    public RoomTypeController(RoomTypeRepo roomTypeRepo) {
//        this.roomTypeRepo = roomTypeRepo;
//    }

//    @RequestMapping("room/addbeds")
//    public String addBeds(@RequestParam Long roomTypeId, @RequestParam int beds) {
//        RoomType roomType = roomTypeRepo.findById(roomTypeId).get();
//        int availableBeds = roomType.getExtraBedsAvailable();
//
//        if (availableBeds != 0 && availableBeds <= beds) {
//            roomType.setBedCount(roomType.getBedCount() + beds);
//            roomType.setExtraBedsAvailable(availableBeds - beds);
//            roomTypeRepo.save(roomType);
//            return "Added " + beds + " beds " + " to " + roomType.getName() + " with id: " + roomTypeId;
//        }
//        else if(roomType.getName().equals("Single room")){
//                return "This is a Single room, you cant add any extra beds to this room";
//            }
//        else {
//            return "There is " + roomType.getExtraBedsAvailable() + " extra beds available";
//        }
//    }

//    @PostMapping("room/addbeds")
//    public String addBeds(@RequestParam Long roomTypeId, @RequestParam int beds) {
//        RoomType roomType = roomTypeRepo.findById(roomTypeId).get();
//        int availableBeds = roomType.getExtraBedsAvailable();
//
//        if (availableBeds != 0 && availableBeds <= beds) {
//            roomType.setBedCount(roomType.getBedCount() + beds);
//            roomType.setExtraBedsAvailable(availableBeds - beds);
//            roomTypeRepo.save(roomType);
//            return "index";
////            return "Added " + beds + " beds " + " to " + roomType.getName() + " with id: " + roomTypeId;
//        }
//        else if(roomType.getName().equals("Single room")){
//            return "This is a Single room, you cant add any extra beds to this room";
//        }
//        else {
//            return "There is " + roomType.getExtraBedsAvailable() + " extra beds available";
//        }
//    }
    @PostMapping("room/addbeds")
    public String addBeds(@RequestParam Long roomTypeId, @RequestParam int beds) {
        return roomService.addBeds(roomTypeId, beds);
    }

}

