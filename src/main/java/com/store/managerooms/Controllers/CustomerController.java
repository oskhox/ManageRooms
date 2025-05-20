package com.store.managerooms.Controllers;

import com.store.managerooms.Repos.CustomerRepository;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
