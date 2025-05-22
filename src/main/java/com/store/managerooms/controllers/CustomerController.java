package com.store.managerooms.controllers;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping("/allCustomers")
    public List<DetailedCustomerDto> allCustomers() {
        return customerService.allCustomers();
    }

    //Id i URL, PUT-request i JSON utan id
    @PutMapping("/changeCustomer/{id}")
    public DetailedCustomerDto changeCustomer(@PathVariable String id, @Valid @RequestBody DetailedCustomerDto detailedCustomerDto) {
        detailedCustomerDto.setId(Long.parseLong(id));
        return customerService.changeCustomer(detailedCustomerDto);
    }

    //TODO: Ta bort kund
    //TODO: Läs minimal DTO till booking, skicka in id

    @PostMapping("/addCustomer")
    public DetailedCustomerDto addCustomer(@Valid @RequestBody DetailedCustomerDto d) {
        return customerService.addCustomer(d);
    }
}