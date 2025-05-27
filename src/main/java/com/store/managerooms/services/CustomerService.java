package com.store.managerooms.services;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.dtos.MinimalCustomerDto;
import com.store.managerooms.models.Customer;

import java.util.List;

public interface CustomerService {
    List<DetailedCustomerDto> allCustomers();
    void addCustomer(DetailedCustomerDto c);
    DetailedCustomerDto changeCustomer(DetailedCustomerDto detailedCustomerDto);
    String deleteCustomer(Long id);
    Customer findByCustomerId(Long id);
    MinimalCustomerDto customerToDTO(Customer c);
    List<MinimalCustomerDto> allCustomersMinimal();
    DetailedCustomerDto findDTOByCustomerId(Long id);
    }