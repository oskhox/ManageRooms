package com.store.managerooms;

import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.RoomRepo;
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
    public CommandLineRunner addingRooms(RoomRepo repo) {
        return args -> {

            Room room1 = new Room(1001, new RoomType("Double",2,0,true));
            Room room2 = new Room(1002, new RoomType("Double",2,0,true));
            Room room3 = new Room(1003, new RoomType("Double",2,0,true));
            Room room4 = new Room(1004, new RoomType("Single",1));
            Room room5 = new Room(1005, new RoomType("Single",1));
            Room room6 = new Room(1006, new RoomType("Single",1));

            repo.save(room1);
            repo.save(room2);
            repo.save(room3);
            repo.save(room4);
            repo.save(room5);
            repo.save(room6);

        };
    }

}