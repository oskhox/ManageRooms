package com.store.managerooms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/bookingform")
    public String showBookingForm() {
        return "bookingform";
    }
}
