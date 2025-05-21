package com.store.managerooms.Services;

import com.store.managerooms.DTOs.DetailedCustomerDTO;
import com.store.managerooms.DTOs.MinimalCustomerDTO;
import com.store.managerooms.Models.Customer;
import com.store.managerooms.Repos.CustomerRepository;
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
    public List<DetailedCustomerDTO> allCustomers() {
        return customerRepo.findAll().stream().map(this::customerToDetailedCustomerDTO).toList();
    }

    @Override
    public DetailedCustomerDTO addCustomer(DetailedCustomerDTO d) {
        Customer saved = customerRepo.save(detailedCustomerDTOToCustomer(d));
        return customerToDetailedCustomerDTO(saved);
    }

    @Override
    public void changeCustomer(Long id) {
    }

    @Override
    public void deleteCustomer(Long id) {
    }

    /*
       OMVANDLINGSMETODER
    */

    public DetailedCustomerDTO customerToDetailedCustomerDTO(Customer c) {
        return new DetailedCustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone());
    }

    //TODO: Anropa i booking sen
    public MinimalCustomerDTO customerToDTO(Customer c) {
        return new MinimalCustomerDTO(c.getId(), c.getFirstName(), c.getLastName());
    }

    public Customer detailedCustomerDTOToCustomer(DetailedCustomerDTO d) {
        return new Customer(d.getId(), d.getFirstName(), d.getLastName(), d.getEmail(), d.getPhone());
    }
}