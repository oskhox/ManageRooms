package com.store.managerooms;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

    @RequestMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id){
        bookingService.deleteBookingById(id);
        return "Bokning  " + id + " är raderad";
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

