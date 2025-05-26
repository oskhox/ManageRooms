package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer")
    public String allCustomers(Model model) {
        model.addAttribute("allCustomers", customerService.allCustomers());
        return "customer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute DetailedCustomerDto c) {
        customerService.addCustomer(c);
        return "redirect:/customer";
    }

    @PostMapping("/changeCustomer")
    public String changeCustomer(@ModelAttribute DetailedCustomerDto detailedCustomerDto) {
        customerService.changeCustomer(detailedCustomerDto);
        return "redirect:/customer";
    }

    @PostMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("deleteId") String id) {
        customerService.deleteCustomer(Long.parseLong(id));
        return "redirect:/customer";
    }
}