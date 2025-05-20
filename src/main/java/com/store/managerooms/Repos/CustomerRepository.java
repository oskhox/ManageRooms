package com.store.managerooms.Repos;

import com.store.managerooms.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Hibernate-metod
    public Customer findByEmail(String email);
}