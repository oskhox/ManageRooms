package com.store.managerooms.controllers;

import com.store.managerooms.dtos.AddBedsDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.services.CustomerService;
import com.store.managerooms.services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RoomTypeController {

    private final RoomService roomService;
    private final CustomerService customerService;

    @PostMapping("room/addbeds")
    public ResponseEntity<String> addBeds(@ModelAttribute @Valid AddBedsDto addBedsDto,Model model) {

        roomService.addBeds(addBedsDto);
//        redirectAttributes.addFlashAttribute("bedMessage", "Extra säng(ar) tillagda!");

        model.addAttribute("availableRooms", "Available rooms");
        model.addAttribute("labelRooms", "Tillgängliga rum");
        model.addAttribute("pageTitle", "Skapa bokning");
        model.addAttribute("formTitle", "Bokningsformulär");
        model.addAttribute("minimalBookingDto", new MinimalBookingDto());
        model.addAttribute("customers", customerService.allCustomers());
        model.addAttribute("labelCustomer", "Välj kund:");
        model.addAttribute("labelStartDate", "Startdatum:");
        model.addAttribute("labelEndDate", "Slutdatum:");

        return ResponseEntity.ok("Extra säng(ar) tillagda!");
    }



















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

}

