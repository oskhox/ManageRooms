package com.store.managerooms;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final RoomService roomService;


    @GetMapping("")
    public String getBookings(Model model) {
        List<DetailedBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }


    @PostMapping("/create")
    public String createBooking(@ModelAttribute BookingDto bookingDto, Model model) {
        BookingDto savedBookingDto = bookingService.saveBooking(bookingDto);
        model.addAttribute("booking", savedBookingDto);
        return "bookingConfirmation";
    }



    //inte gjort dto än

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam Long id, Model model) {
        bookingService.deleteBookingById(id);
        model.addAttribute("message", "Bokning " + id + " är raderad");
        return "cancelled";
    }

    @RequestMapping("/booked-room/")
    public String findBooking(@RequestParam Long id, Model model) {
        try {
            Booking booking = bookingService.findBookingById(id);
            model.addAttribute("booking", booking);
            return "bookingDetails";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "bookings";
        }
    }

        @PostMapping("/booked-room/")
        public String handleBooking(@RequestParam Long id, Model model) {
            findBooking(id,model);
        }

    }

