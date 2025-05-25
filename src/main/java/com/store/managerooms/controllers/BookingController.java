package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.services.BookingService;
import com.store.managerooms.services.CustomerService;
import com.store.managerooms.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final CustomerService customerService;
    private final RoomService roomService;


    @RequestMapping("booked-rooms/")
    public String isRoomBooked(@RequestParam("roomId") Long roomId,
                               @RequestParam("startDate") LocalDate startDate,
                               @RequestParam("endDate") LocalDate endDate, Model model) {
        boolean booked = bookingService.isRoomBooked(roomId, startDate, endDate);
        model.addAttribute("bookedRoom", booked);
        return "bookings";
    }

    @RequestMapping
    public String getBookings(Model model) {
        List<MinimalBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        model.addAttribute("pageTitle", "Alla bokningar");
        model.addAttribute("formTitle", "Skapa en ny bokning");
        model.addAttribute("startDate", "Startdatum");
        model.addAttribute("endDate", "Slutdatum");
        model.addAttribute("roomNumber", "Rumsnummer");
        model.addAttribute("options", "Dina val");
        return "bookings";
    }

    @GetMapping("create")
    public String showBookingForm(Model model) {
        model.addAttribute("customers", customerService.allCustomers());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("detailedBookingDto", new DetailedBookingDto());
        model.addAttribute("pageTitle", "Skapa en bokning");
        model.addAttribute("formTitle", "Fyll i bokningsformuläret");
        model.addAttribute("labelRooms", "Välj rum:");
        model.addAttribute("labelCustomer", "Välj rum:");
        model.addAttribute("labelStartDate", "Välj startdatum:");
        model.addAttribute("labelEndDate", "Välj slutdatum:");


        return "create";
    }

    @PostMapping("create")
    public String createBooking(@ModelAttribute DetailedBookingDto detailedBookingDto, RedirectAttributes redirectAttributes, Model model) {
        try {
            DetailedBookingDto newBooking = bookingService.createNewBooking(detailedBookingDto);
            model.addAttribute("booking", newBooking);
            redirectAttributes.addFlashAttribute("message", "Bokning skapad!");
            return "redirect:/bookings";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());

            model.addAttribute("customers", customerService.allCustomers());
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("detailedBookingDto", detailedBookingDto);
            return "create";
        }
    }

    @GetMapping("update-booking/{id}")
    public String showFormForExistingBooking(@PathVariable Long id, Model model) {
        MinimalBookingDto booking = bookingService.findMinimalBookingById(id);
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("minimalBookingDto", booking);
        model.addAttribute("pageTitle", "Uppdatera en bokning");
        model.addAttribute("formTitle", "Ändra uppgifter i formuläret");
        model.addAttribute("labelRooms", "Välj rum:");
        model.addAttribute("labelStartDate", "Välj startdatum:");
        model.addAttribute("labelEndDate", "Välj slutdatum:");


        return "update-booking";
    }

    @PostMapping("update-booking")
    public String updateBooking(@ModelAttribute MinimalBookingDto minimalBookingDto,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        try {
            bookingService.updateExistingBooking(minimalBookingDto);
            redirectAttributes.addFlashAttribute("message", "Bokningen är uppdaterad!");
            return "redirect:/bookings";
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("minimalBookingDto", minimalBookingDto);
            return "update-booking";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookingService.deleteBookingById(id);
        redirectAttributes.addFlashAttribute("message", "Bokningen är borttagen!");
        return "redirect:/bookings";
    }

}