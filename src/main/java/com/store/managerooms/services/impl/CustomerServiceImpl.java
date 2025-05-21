package com.store.managerooms.services.impl;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.dtos.MinimalCustomerDto;
import com.store.managerooms.models.Customer;
import com.store.managerooms.repos.CustomerRepository;
import com.store.managerooms.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.customerRepo = repo;
    }

    /*
    INTERAKTIONSMETODER
    */
    @Override
    public List<DetailedCustomerDto> allCustomers() {
        return customerRepo.findAll().stream().map(this::customerToDetailedCustomerDTO).toList();
    }

    @Override
    public DetailedCustomerDto addCustomer(DetailedCustomerDto d) {
        Customer saved = customerRepo.save(detailedCustomerDTOToCustomer(d));
        return customerToDetailedCustomerDTO(saved);
    }

    @Override
    public void changeCustomer(Long id) {
    }

    @Override
    public void deleteCustomer(Long id) {
    }

    public Customer findByCustomerId(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    /*
       OMVANDLINGSMETODER
    */

    public DetailedCustomerDto customerToDetailedCustomerDTO(Customer c) {
        return new DetailedCustomerDto(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone());
    }

    //TODO: Anropa i booking sen
    public MinimalCustomerDto customerToDTO(Customer c) {
        return new MinimalCustomerDto(c.getId(), c.getFirstName(), c.getLastName());
    }

    public Customer detailedCustomerDTOToCustomer(DetailedCustomerDto d) {
        return new Customer(d.getId(), d.getFirstName(), d.getLastName(), d.getEmail(), d.getPhone());
    }
}