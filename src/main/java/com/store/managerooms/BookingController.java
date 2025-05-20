package com.store.managerooms;

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
    private final CustomerService customerService;
    private final RoomService roomService;
    private final BookingServiceImpl bookingServiceImpl;


    @GetMapping("")
    public String getBookings(Model model) {
        List<DetailedBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "bookings";
    }


    @PostMapping("/create")
    public String createBooking(@ModelAttribute DetailedBookingDto detailedBookingDto, Model model) {
        DetailedBookingDto savedBookingDto = bookingService.saveBooking(detailedBookingDto);
        model.addAttribute("booking", savedBookingDto);
        return "bookingConfirmation";
    }


    @PostMapping("/cancel")
    public String cancelBooking(@ModelAttribute Long id, Model model) {
        bookingService.deleteBookingById(id);
        model.addAttribute("message", "Bokning " + id + " är raderad");
        return "cancelled";



        //inte gjort dto än
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

