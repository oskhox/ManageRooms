package com.store.managerooms.RoomTest;

import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.RoomRepo;
import com.store.managerooms.repos.RoomTypeRepo;
import com.store.managerooms.services.RoomService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class RoomServiceImplTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private RoomTypeRepo roomTypeRepo;

    @Autowired
    private RoomService roomService;

    RoomType roomType1 = new RoomType("Enkelrum", 1, 0);
    RoomType roomType2 = new RoomType("Dubbelrum", 2, 1);

    Room room1 = new Room();
    Room room2 = new Room();

    @BeforeEach
    public void setup() {
        bookingRepository.deleteAll();
        roomRepo.deleteAll();
        roomTypeRepo.deleteAll();

        roomTypeRepo.save(roomType1);
        roomTypeRepo.save(roomType2);

        room1.setRoomNumber(1011);
        room1.setRoomType(roomType1);
        roomRepo.save(room1);

        room2.setRoomNumber(1012);
        room2.setRoomType(roomType2);
        roomRepo.save(room2);
    }

    @Test
    public void getAllRoomsTest() throws Exception {
        List<RoomDto> savedRooms = roomService.getAllRooms();
        assertTrue(savedRooms.size() >= 2);
        assertTrue(savedRooms.stream().anyMatch(r -> r.getRoomNumber() == 1011));
        assertTrue(savedRooms.stream().anyMatch(r -> r.getRoomNumber() == 1012));
    }
}