package com.store.managerooms.controllers;

import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.RoomRepo;
import com.store.managerooms.repos.RoomTypeRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RoomController {

    private final RoomRepo roomRepo;
    private final BookingRepository bookingRepo;


    public RoomController(RoomRepo roomRepo , BookingRepository bookingRepo) {
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;

    }

    @RequestMapping("rooms")
    public List<Room> getAllRooms(){
        return roomRepo.findAll();
    }

    @RequestMapping("rooms/search")
    public List<Room> getAvailableRooms(@RequestParam int peopleCount, @RequestParam LocalDate start, @RequestParam LocalDate end) {
        List<Room> availableRooms = new ArrayList<>();
        long idCounter = 1;
        for (Room room : roomRepo.findAll()) {
            if(bookingRepo.isDateBookedCheckExistingBooking(room.getRoomId(), start,end,idCounter)) {
                System.out.println("Room with id" + room.getRoomId() + " is already booked");
            } else {
                int peopleInRoom = room.getRoomType().getBedCount() + room.getRoomType().getExtraBedsAvailable();
                if(peopleInRoom >= peopleCount) {
                    availableRooms.add(room);
                }

            }
            idCounter++;
            }

        return availableRooms;
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
