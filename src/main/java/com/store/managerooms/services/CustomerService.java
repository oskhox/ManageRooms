package com.store.managerooms.services;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.models.Customer;

import java.util.List;

public interface CustomerService {
    List<DetailedCustomerDto> allCustomers();
    DetailedCustomerDto addCustomer(DetailedCustomerDto c);
    void changeCustomer(Long id);
    void deleteCustomer(Long id);
    Customer findByCustomerId(Long id);
}