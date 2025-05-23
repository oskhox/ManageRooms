package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.models.Customer;
import com.store.managerooms.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<DetailedCustomerDto> allCustomers() {
        return customerService.allCustomers();
    }

    @PostMapping("/addCustomer")
    public DetailedCustomerDto addCustomer(@Valid @RequestBody DetailedCustomerDto d) {
        return customerService.addCustomer(d);
    }

    //id i URL, PUT-request i JSON utan id
    @PutMapping("/changeCustomer/{id}")
    public DetailedCustomerDto changeCustomer(@PathVariable String id, @Valid @RequestBody DetailedCustomerDto detailedCustomerDto) {
        detailedCustomerDto.setId(Long.parseLong(id));
        return customerService.changeCustomer(detailedCustomerDto);
    }

    //returnerar string med bekräftelse
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(Long.parseLong(id));
    }
*/

//MVC-controller
@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    //MVC 1
    @GetMapping("/customer")
    public String allCustomers(Model model) {
        model.addAttribute("allCustomers", customerService.allCustomers());
        model.addAttribute("title", "All customers");
        return "customers";
    }

    //MVC 2
    @PostMapping("/addCustomer")
    public String addCustomer(@ModelAttribute DetailedCustomerDto c, Model model) {
        customerService.addCustomer(c);
        return allCustomers(model);
    }

    //REST 3
    //id i URL, PUT-request i JSON utan id
    @PutMapping("/changeCustomer/{id}")
    public DetailedCustomerDto changeCustomer(@PathVariable String id, @Valid @RequestBody DetailedCustomerDto detailedCustomerDto) {
        detailedCustomerDto.setId(Long.parseLong(id));
        return customerService.changeCustomer(detailedCustomerDto);
    }

    //REST 4
    //returnerar string med bekräftelse
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(Long.parseLong(id));
    }
}
