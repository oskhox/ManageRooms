package com.store.managerooms;

import com.store.managerooms.dtos.BookingDto;
import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.services.BookingService;
import com.store.managerooms.services.impl.BookingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @RequestMapping("")
    public String getBookings(Model model) {
        List<BookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }


    @PostMapping("/create")
    public String createBooking(@ModelAttribute DetailedBookingDto detailedBookingDto, Model model) {
        bookingService.saveNewBooking(detailedBookingDto);
        model.addAttribute("message", "Bokningen är genomförd");
        return "bookings";
    }

    @DeleteMapping("/cancel")
    public String cancelBooking(@ModelAttribute Long id, Model model) {
        bookingService.deleteBookingById(id);
        model.addAttribute("message", "Bokning " + id + " är raderad");
        return "cancelled";

    }


    @RequestMapping ("/booked-room/")
    public String showBooking(@RequestParam Long id, Model model) {
        try {
            DetailedBookingDto booking = bookingService.findBookingById(id);
            model.addAttribute("booked-room", booking);
            return "booked-room";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "bookings";
        }
    }


        @PostMapping("/update-room/")
        public String handleBooking(@ModelAttribute DetailedBookingDto detailedBookingDto, Model model) {
            bookingService.updateExistingBooking(detailedBookingDto);
            model.addAttribute("message", "Bokningen är uppdaterad");
            return "bookings";        }

    }

