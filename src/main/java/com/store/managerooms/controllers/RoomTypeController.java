package com.store.managerooms.controllers;

import com.store.managerooms.repos.RoomTypeRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomTypeController {

    private final RoomTypeRepo roomTypeRepo;

    public RoomTypeController(RoomTypeRepo roomTypeRepo) {
        this.roomTypeRepo = roomTypeRepo;
    }

    @RequestMapping
}
