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

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

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

    @Test
    void testGetAvailableRooms() {
        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2020, 1, 15);
        int peopleCount = 2;

        RoomType roomType1 = new RoomType("Big Double Room",2,2);
        RoomType roomType2 = new RoomType("Double Room",2,1);
        Room room1 = new Room();
        room1.setRoomId(1L);
        room1.setRoomNumber(1001);
        room1.setRoomType(roomType1);

        Room room2 = new Room();
        room2.setRoomId(2L);
        room2.setRoomNumber(1002);
        room2.setRoomType(roomType2);

        when(roomRepo.findAll()).thenReturn(List.of(room1,room2));
        when(bookingRepository.isDateBookedCheckExistingBooking(1L,start,end,(long)1)).thenReturn(true);
        when(bookingRepository.isDateBookedCheckExistingBooking(2L,start,end,(long)2)).thenReturn(false);

        List<RoomDto> availableRooms = roomService.getAvailableRooms(peopleCount, start, end);

        assertThat(availableRooms.size()).isEqualTo(1);
        assertThat(availableRooms.getFirst().getRoomNumber()).isEqualTo(1002);
    }
}