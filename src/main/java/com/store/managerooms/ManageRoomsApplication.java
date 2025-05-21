package com.store.managerooms;

import com.store.managerooms.models.Customer;
import com.store.managerooms.repos.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ManageRoomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageRoomsApplication.class, args);
    }

    @Bean
    public CommandLineRunner defaultCustomers(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(new Customer(null, "Jack", "Bauer", "jack.bauer@gmail.com", "+4612345"));
            customerRepository.save(new Customer(null, "Chloe", "O'Brian", "chloe.obrian@gmail.com", "+4612346"));
            customerRepository.save(new Customer(null, "Tony", "Almeida", "tony.almeida@gmail.com", "+4612347"));
            customerRepository.save(new Customer(null, "Kim", "Smith", "kim.smith@gmail.com", "+4612348"));
            customerRepository.save(new Customer(null, "Laura", "Palmer", "laura.palmer@gmail.com", "+4612349"));
        };
    }
}