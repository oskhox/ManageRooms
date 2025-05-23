package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.services.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

/*
    @RequestMapping("booked-rooms")
    public String isRoomBooked(@RequestParam("roomId")Long roomId,
                                @RequestParam("startDate") LocalDate startDate,
                                @RequestParam("endDate") LocalDate endDate, Model model) {
        boolean booked = bookingService.isRoomBooked(roomId, startDate, endDate);
        model.addAttribute("bookedRoom", booked);
        return "";
    }


    @RequestMapping("")
    public String getBookings(Model model) {
        List<MinimalBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @RequestMapping ("/booked-room/")
    public String showBooking(@RequestParam Long id, Model model) {
        try {
            DetailedBookingDto booking = bookingService.findBookingById(id);
            model.addAttribute("pageTitle", "Bokat rum");
            model.addAttribute("booked-room", booking);
            return "booked-room";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Bokningen hittades ej");
            return "bookings";
        }
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute DetailedBookingDto detailedBookingDto, Model model) {
        try {
            bookingService.createNewBooking(detailedBookingDto);
            model.addAttribute("pageTitle", "Skapa en bokning");
            model.addAttribute("message", "Bokningen är genomförd");
            return "bookings";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "create";
        }
    }

    @PostMapping("/booked-room/update")
    public String updateBooking(@ModelAttribute MinimalBookingDto minimalBookingDto, Model model) {
        try {
        bookingService.updateExistingBooking(minimalBookingDto);
        model.addAttribute("pageTitle", "Uppdatera bokningen");
        model.addAttribute("message", "Bokningen är uppdaterad");
            return "booked-room";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/booked-room/update";

        }
    }

    @DeleteMapping("/booked-room")
    public String cancelBooking(@RequestParam Long id, Model model) {
        bookingService.deleteBookingById(id);
        model.addAttribute("message", "Bokning " + id + " är raderad");
        return "bookings";

    }

*/

    //testa
    @GetMapping("/booked-rooms")
    public boolean isRoomBooked(@RequestParam("roomId") Long roomId,
                                @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return bookingService.isRoomBooked(roomId, startDate, endDate);
    }

    @GetMapping
    public List<MinimalBookingDto> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/booked-room")
    public DetailedBookingDto showBooking(@RequestParam Long id) {
        return bookingService.findBookingById(id);
    }

    @PostMapping("/create")
    public DetailedBookingDto createBooking(@Valid @RequestBody DetailedBookingDto detailedBookingDto) {
        return bookingService.createNewBooking(detailedBookingDto);

    }

    @PutMapping("/update")
    public MinimalBookingDto updateBooking(@Valid @RequestBody MinimalBookingDto minimalBookingDto) {
        return bookingService.updateExistingBooking(minimalBookingDto);
    }

    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return "Bokning är raderad";
    }

}




