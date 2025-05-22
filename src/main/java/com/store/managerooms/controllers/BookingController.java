package com.store.managerooms.controllers;

/*
import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

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
            model.addAttribute("errorMessage", "Bokning " + id + " hittades ej");
            return "bookings";
        }
    }

    @PostMapping("/create")
    public String createBooking(@ModelAttribute DetailedBookingDto detailedBookingDto, Model model) {
        bookingService.saveNewBooking(detailedBookingDto);
        model.addAttribute("pageTitle", "Skapa en bokning");
        model.addAttribute("message", "Bokningen är genomförd");
        return "bookings";
    }

    @PostMapping("/booked-room/update/")
    public String updateBooking(@ModelAttribute DetailedBookingDto detailedBookingDto, Model model) {
        bookingService.updateExistingBooking(detailedBookingDto);
        model.addAttribute("pageTitle", "Uppdatera bokningen");
        model.addAttribute("message", "Bokningen är uppdaterad");
        return "booked-room";
    }


    @DeleteMapping("/booked-room")
    public String cancelBooking(@RequestParam Long id, Model model) {
        bookingService.deleteBookingById(id);
        model.addAttribute("message", "Bokning " + id + " är raderad");
        return "bookings";

    }

    //för att testa med pathvariable

    @RequestMapping("/booked-room/{id}")
    public String showBookingTest(@PathVariable Long id, Model model) {
        try {
            DetailedBookingDto booking = bookingService.findBookingById(id);
            model.addAttribute("pageTitle", "Bokat rum");
            model.addAttribute("booked-room", booking);
            return "booked-room";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Bokning " + id + " hittades ej");
            return "/bookings";
        }
    }
}

*/

