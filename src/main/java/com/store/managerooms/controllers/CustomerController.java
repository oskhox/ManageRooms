package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/allCustomers")
    public List<DetailedCustomerDto> allCustomers() {
        return customerService.allCustomers();
    }

    //TODO: Läs minimal DTO till booking, skicka in id

    @PostMapping("/addCustomer")
    public DetailedCustomerDto addCustomer(@Valid @RequestBody DetailedCustomerDto d) {
        return customerService.addCustomer(d);
    }

    //TODO: Ändra kund
    //TODO: Ta bort kund
}
