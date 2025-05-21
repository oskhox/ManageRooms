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
    //läs ut alla fullständiga kunder från databasen och gör dem till dto:s
    public List<DetailedCustomerDTO> allCustomers() {
        return customerRepo.findAll().stream().map(this::customerToDetailedCustomerDTO).toList();
    }

    //konvertera till customer, spara ner kund i databasen, returnera sparad DTO
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

    //3A. Omvandlar från Entitets-objekt till DTO-objekt för att läsa
    //Från Kund-objekt till stor kund-DTO
    public DetailedCustomerDTO customerToDetailedCustomerDTO(Customer c) {
        return new DetailedCustomerDTO(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone());
    }

    //Från Kund-objekt till liten kund-DTO, anropa i booking sen
    public MinimalCustomerDTO customerToDTO(Customer c) {
        return new MinimalCustomerDTO(c.getId(), c.getFirstName(), c.getLastName());
    }

    //3B. Omvandlar från stort DTO-objekt till stort Entitetsobjekt för att kunna skriva
    public Customer detailedCustomerDTOToCustomer(DetailedCustomerDTO d) {
        return new Customer(d.getId(), d.getFirstName(), d.getLastName(), d.getEmail(), d.getPhone());
    }
}