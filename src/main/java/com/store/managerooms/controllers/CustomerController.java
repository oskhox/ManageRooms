package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer")
    public String allCustomers(Model model) {
        model.addAttribute("detailedCustomerDto", new DetailedCustomerDto());
        model.addAttribute("allCustomers", customerService.allCustomers());
        return "customer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@Valid @ModelAttribute DetailedCustomerDto d, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allCustomers", customerService.allCustomers());
            return "customer";
        }
        customerService.addCustomer(d);
        return "redirect:/customer";
    }

    @GetMapping("/editCustomer")
    public String showForm(@RequestParam("id") Long id, Model model) {
        DetailedCustomerDto customerDto = customerService.findDTOByCustomerId(id);
        model.addAttribute("editCustomer", customerDto);
        model.addAttribute("allCustomers", customerService.allCustomers());
        model.addAttribute("detailedCustomerDto", new DetailedCustomerDto());
        return "customer";
    }

    @PostMapping("/editCustomer")
    public String editCustomer(@ModelAttribute DetailedCustomerDto dto) {
        customerService.changeCustomer(dto);
        return "redirect:/customer";
    }

    @PostMapping("/deleteCustomer")
    public String deleteCustomer(@RequestParam("deleteId") String id) {
        customerService.deleteCustomer(Long.parseLong(id));
        return "redirect:/customer";
    }
}