package com.store.managerooms;

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
public class BookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final RoomService roomService;


    public BookingController(BookingService bookingService, CustomerService customerService, RoomService roomService) {
        this.bookingService = bookingService;
        this.customerService = customerService;
        this.roomService = roomService;
    }

    @PostMapping("/cancel")
    public String cancelBooking(@RequestParam Long id, Model model) {
        bookingService.deleteBookingById(id);
        model.addAttribute("message", "Bokningen " + id + " är raderad");
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


    @RequestMapping("")
    public List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }

    @PostMapping("/create")
    public String createBooking(@RequestParam String customerName,
                                @RequestParam String customerAdress,
                                @RequestParam String customerEmail,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                @RequestParam Long roomId,
                                Model model) {

        Customer customer = customerService.createCustomer(customerName, customerAdress, customerEmail);
        Room room = roomService.findById(roomId);

        Booking booking = new Booking();
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setCustomer(customer);
        booking.setRoom(room);

        Booking savedBooking = bookingService.saveBooking(booking);

        model.addAttribute("booking", savedBooking);
            return "bookingConfirmation";
        }

    }

