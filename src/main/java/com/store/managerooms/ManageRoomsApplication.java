package com.store.managerooms;

import com.store.managerooms.models.Customer;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
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

            Room room1 = new Room(1001,new RoomType("Single",1));
            Room room2 = new Room(1002,new RoomType("Single",1));
            Room room3 = new Room(1003,new RoomType("Double",2,0,true));
            Room room4 = new Room(1004,new RoomType("Double",2,0,true));
            Room room5 = new Room(1005,new RoomType("Big Double",2,0,true));
            Room room6 = new Room(1006,new RoomType("Big Double",2,0,true));
        };
    }
}