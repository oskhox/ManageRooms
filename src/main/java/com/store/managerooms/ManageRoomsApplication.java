package com.store.managerooms;

import com.store.managerooms.models.Booking;
import com.store.managerooms.models.Customer;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.CustomerRepository;
import com.store.managerooms.repos.RoomRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class ManageRoomsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageRoomsApplication.class, args);
    }

    @Bean
    public CommandLineRunner defaultCustomers(CustomerRepository customerRepository, RoomRepo roomRepo, BookingRepository bookingRepo) {
        return args -> {

            customerRepository.save(new Customer(null, "Jack", "Bauer", "jack.bauer@gmail.com", "+4612345"));
            customerRepository.save(new Customer(null, "Chloe", "O'Brian", "chloe.obrian@gmail.com", "+4612346"));
            customerRepository.save(new Customer(null, "Tony", "Almeida", "tony.almeida@gmail.com", "+4612347"));
            customerRepository.save(new Customer(null, "Kim", "Smith", "kim.smith@gmail.com", "+4612348"));

            roomRepo.save(new Room(1001,new RoomType("Enkelrum",1)));
            roomRepo.save(new Room(1002,new RoomType("Enkelrum",1)));
            roomRepo.save(new Room(1003,new RoomType("Dubbelrum",2,1)));
            roomRepo.save(new Room(1004,new RoomType("Dubbelrum",2,1)));
            roomRepo.save(new Room(1005,new RoomType("Extra stort dubbelrum",2,2)));
            roomRepo.save(new Room(1006,new RoomType("Extra stort dubbelrum",2,2)));

            Room testRoom = new Room(1010,new RoomType("Dubbelrum",2,1));
            roomRepo.save(testRoom);
            Customer testCustomer = new Customer(null, "Laura", "Palmer", "laura.palmer@gmail.com", "+4612349");
            customerRepository.save(testCustomer);

            Booking testBooking = new Booking();
            testBooking.setStartDate(LocalDate.parse("2025-01-20"));
            testBooking.setEndDate(LocalDate.parse("2025-01-25"));
            testBooking.setCustomer(testCustomer);
            testBooking.setRoom(testRoom);
            bookingRepo.save(testBooking);

        };
    }
}