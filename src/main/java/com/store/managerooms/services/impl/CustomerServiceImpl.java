package com.store.managerooms.services.impl;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.dtos.MinimalCustomerDto;
import com.store.managerooms.models.Booking;
import com.store.managerooms.models.Customer;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.CustomerRepository;
import com.store.managerooms.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepo;
    @Autowired
    BookingRepository bookingsRepo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.customerRepo = repo;
    }

    /*
    INTERAKTIONSMETODER
    */
    @Override
    public List<DetailedCustomerDto> allCustomers() {
        return customerRepo.findAll().stream().map(this::customerToDetailedCustomerDto).toList();
    }

    @Override
    public DetailedCustomerDto addCustomer(DetailedCustomerDto d) {
        Customer saved = customerRepo.save(detailedCustomerDtoToCustomer(d));
        return customerToDetailedCustomerDto(saved);
    }

    @Override
    public DetailedCustomerDto changeCustomer(DetailedCustomerDto d) {
        Long id = d.getId();
        Customer customerToChange = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        Customer updatedDetails = detailedCustomerDtoToCustomer(d);
        customerToChange.setFirstName(updatedDetails.getFirstName());
        customerToChange.setLastName(updatedDetails.getLastName());
        customerToChange.setEmail(updatedDetails.getEmail());
        customerToChange.setPhone(updatedDetails.getPhone());
        customerRepo.save(customerToChange);

        Customer updatedCustomer = customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return customerToDetailedCustomerDto(updatedCustomer);
    }

    @Override
    public String deleteCustomer(Long id) {
        List<Booking> AllBookings = bookingsRepo.findAll();

        for (Booking b : AllBookings) {
            if (b.getCustomer().getId().equals(id)) {
                customerRepo.deleteById(id);
                return "Customer deleted";
            }
        }
        return "Customer not found with id: " + id;
    }

    public Customer findByCustomerId(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    /*
       OMVANDLINGSMETODER
    */
    public DetailedCustomerDto customerToDetailedCustomerDto(Customer c) {
        return new DetailedCustomerDto(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getPhone());
    }

    public Customer detailedCustomerDtoToCustomer(DetailedCustomerDto d) {
        return new Customer(d.getId(), d.getFirstName(), d.getLastName(), d.getEmail(), d.getPhone());
    }

    //TODO: Ev. anropa i booking sen, annars ta bort
    public MinimalCustomerDto customerToDTO(Customer c) {
        return new MinimalCustomerDto(c.getId(), c.getFirstName(), c.getLastName());
    }
}