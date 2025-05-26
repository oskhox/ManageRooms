package com.store.managerooms.controllers;


import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;
import com.store.managerooms.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("rooms")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("rooms/searchform")
    public String showSearchForm(){
        return "available-rooms";
    }

    @GetMapping("rooms/search")
    public String getAvailableRooms(@RequestParam int peopleCount,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end, Model model) {
        List<Room> availableRooms = roomService.getAvailableRooms(peopleCount, start, end);
        model.addAttribute("availableRooms", availableRooms);

        return "available-rooms";

    }









    //    @RequestMapping("rooms")
//    public List<Room> getAllRooms(){
//        return roomRepo.findAll();
//    }

//    @RequestMapping("rooms/add")
//    public List<Room> addRoom(@RequestParam int roomNumber,
//                              @RequestParam int beds,
//                              @RequestParam int extraBedsAvailable){
//
//        if(extraBedsAvailable == 1 && beds == 2 ) {
//            Room doubleRoom = new Room(roomNumber, new RoomType("Double room", beds, extraBedsAvailable));
//            roomRepo.save(doubleRoom);
//        }
//        else if(extraBedsAvailable == 2 && beds == 2 ) {
//            Room bigDoubleRoom = new Room(roomNumber, new RoomType("Big double room", beds, extraBedsAvailable));
//            roomRepo.save(bigDoubleRoom);
//            }
//        else if(beds == 1 && extraBedsAvailable == 0) {
//            Room room = new Room(roomNumber, new RoomType("Single room",beds));
//            roomRepo.save(room);
//        }
//        return roomRepo.findAll();
//    }

    //    @RequestMapping("rooms/search")
//    public List<Room> getAvailableRooms(@RequestParam int peopleCount, @RequestParam LocalDate start, @RequestParam LocalDate end) {
//        List<Room> availableRooms = new ArrayList<>();
//        long idCounter = 1;
//        for (Room room : roomRepo.findAll()) {
//            if(bookingRepo.isDateBookedCheckExistingBooking(room.getRoomId(), start,end,idCounter)) {
//                System.out.println("Room with id" + room.getRoomId() + " is already booked");
//            } else {
//                int peopleInRoom = room.getRoomType().getBedCount() + room.getRoomType().getExtraBedsAvailable();
//                if(peopleInRoom >= peopleCount) {
//                    availableRooms.add(room);
//                }
//            }
//            idCounter++;
//            }
//        return availableRooms;
//    }
}

