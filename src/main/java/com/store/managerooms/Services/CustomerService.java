package com.store.managerooms.Services;

import com.store.managerooms.DTOs.DetailedCustomerDTO;
import java.util.List;

public interface CustomerService {
    List<DetailedCustomerDTO> allCustomers();
    DetailedCustomerDTO addCustomer(DetailedCustomerDTO c);
    void changeCustomer(Long id);
    void deleteCustomer(Long id);
}