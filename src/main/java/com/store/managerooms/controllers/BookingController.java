package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.services.BookingService;
import com.store.managerooms.services.CustomerService;
import com.store.managerooms.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    private final CustomerService customerService;
    private final RoomService roomService;


    @GetMapping("/booking/{id}")
    public String showBooking(@PathVariable Long id, Model model) {
        try {
            DetailedBookingDto booking = bookingService.findDetailedBookingById(id);
            model.addAttribute("booking", booking);
            model.addAttribute("pageTitle", "Bokningsdetaljer");
            model.addAttribute("customerName", "Kundnamn");
            model.addAttribute("customerEmail", "Email");
            model.addAttribute("customerPhoneNumber", "Telefonnummer");
            model.addAttribute("startDate", "Startdatum");
            model.addAttribute("endDate", "Slutdatum");
            model.addAttribute("roomNumber", "Rumsnummer");
            model.addAttribute("typeOfRoom", "Rumstyp");
            model.addAttribute("totalPeople", "Antal personer");
            //model.addAttribute("availableBeds", "Antal extra sängar");
            model.addAttribute("bookingDetails", "Bokningsinformation");
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Bokning hittades inte.");
        }
        return "booking";
    }

    @RequestMapping
    public String getBookings(Model model) {
        List<MinimalBookingDto> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        model.addAttribute("pageTitle", "Alla bokningar");
        model.addAttribute("formTitle", "Samtliga bokningar");
        model.addAttribute("customerName", "Kundnamn");
        model.addAttribute("roomNumber", "Rumsnummer");
        model.addAttribute("startDate", "Bokat startdatum");
        model.addAttribute("endDate", "Bokat slutdatum");
        model.addAttribute("options", "Dina val");
        return "bookings";
    }

    private void addFormAttributes(Model model) {
        model.addAttribute("customers", customerService.allCustomersMinimal());
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("labelCustomer", "Välj kund:");
    }

    @GetMapping("create")
    public String showBookingForm(Model model) {
        model.addAttribute("minimalBookingDto", new MinimalBookingDto());
        addFormAttributes(model);
        model.addAttribute("pageTitle", "Skapa en bokning");
        return "create";
    }

    @PostMapping("create")
    public String createBooking(@Valid @ModelAttribute MinimalBookingDto minimalBookingDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        if (bindingResult.hasErrors()) {
            addFormAttributes(model);
            return "create";
        }

        try {
            MinimalBookingDto savedBooking = bookingService.createNewBooking(minimalBookingDto);
            redirectAttributes.addFlashAttribute("bookingMessage", "Bokning skapad!");
            return "redirect:/bookings/booking/" + savedBooking.getId();
        } catch (Exception e) {
            addFormAttributes(model);
            model.addAttribute("errorMessage", "Försök igen: " + e.getMessage());
            return "create";
        }
    }


    @GetMapping("/update-booking/{id}")
    public String showFormForExistingBooking(@PathVariable Long id, Model model) {
        try {
            MinimalBookingDto booking = bookingService.findMinimalBookingById(id);
            model.addAttribute("minimalBookingDto", booking);
        } catch (NoSuchElementException e) {
            model.addAttribute("errorMessage", "Bokningen hittades inte.");
        }
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("pageTitle", "Uppdatera en bokning");
        model.addAttribute("formTitle", "Ändra uppgifter");
        model.addAttribute("labelRooms", "Välj rum:");
        model.addAttribute("labelStartDate", "Välj startdatum:");
        model.addAttribute("labelEndDate", "Välj slutdatum:");
        return "update-booking";
    }

    @PostMapping("/update-booking")
    public String updateBooking(@Valid @ModelAttribute MinimalBookingDto minimalBookingDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rooms", roomService.getAllRooms());
            model.addAttribute("minimalBookingDto", minimalBookingDto);
            return "update-booking";
        }

        try {
            MinimalBookingDto booking = bookingService.updateExistingBooking(minimalBookingDto);
            model.addAttribute("minimalBookingDto", booking);
            redirectAttributes.addFlashAttribute("message", "Bokningen är uppdaterad!");
            return "redirect:/bookings";
        } catch (NoSuchElementException | IllegalStateException | IllegalArgumentException e) {
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