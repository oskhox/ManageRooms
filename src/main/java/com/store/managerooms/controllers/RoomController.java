package com.store.managerooms.controllers;


import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;
import com.store.managerooms.services.CustomerService;
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
    private final CustomerService customerService;

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
        List<RoomDto> availableRooms = roomService.getAvailableRooms(peopleCount, start, end);

        MinimalBookingDto bookingDto = new MinimalBookingDto();
        bookingDto.setStartDate(start);
        bookingDto.setEndDate(end);

        model.addAttribute("availableRooms", availableRooms);
        model.addAttribute("labelRooms", "Tillgängliga rum");
        model.addAttribute("pageTitle", "Skapa bokning");
        model.addAttribute("formTitle", "Bokningsformulär");
        model.addAttribute("minimalBookingDto", bookingDto);
        model.addAttribute("customers", customerService.allCustomers());
        model.addAttribute("labelCustomer", "Välj kund:");
        model.addAttribute("labelStartDate", "Startdatum:");
        model.addAttribute("labelEndDate", "Slutdatum:");

        return "create";
    }
}

