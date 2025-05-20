package com.store.managerooms.Controllers;

import com.store.managerooms.Models.Customer;
import com.store.managerooms.Repos.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//gör ev om till controller
@RestController
public class CustomerController {

    private final CustomerRepository customerRepo;
    public CustomerController(CustomerRepository customerRepository) {this.customerRepo = customerRepository;}

    @GetMapping("/customers")
    public String allCustomers() {
        return customerRepo.findAll().toString();
    }

    @PostMapping("/addCustomer")
    public List<Customer> addCustomer(@Valid @RequestBody Customer c) {
        customerRepo.save(c);
        return customerRepo.findAll();
    }
}
