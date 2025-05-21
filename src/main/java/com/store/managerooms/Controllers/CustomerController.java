package com.store.managerooms.Controllers;

import com.store.managerooms.DTOs.DetailedCustomerDTO;
import com.store.managerooms.Services.CustomerService;
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
    public List<DetailedCustomerDTO> allCustomers() {
        return customerService.allCustomers();
    }

    //TODO: Läs minimal DTO till booking

    @PostMapping("/addCustomer")
    public DetailedCustomerDTO addCustomer(@Valid @RequestBody DetailedCustomerDTO d) {
        return customerService.addCustomer(d);
    }

    //TODO: Ändra kund
    //TODO: Ta bort kund
}
