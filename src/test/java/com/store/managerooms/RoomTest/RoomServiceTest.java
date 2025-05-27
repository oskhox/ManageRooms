package com.store.managerooms.RoomTest;
import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.RoomRepo;
import com.store.managerooms.services.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepo roomRepo;

    @InjectMocks
    private RoomServiceImpl roomService;

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
        when(bookingRepository.isDateBookedCheckExistingBooking(eq(1L),eq(start),eq(end),anyLong())).thenReturn(true);
        when(bookingRepository.isDateBookedCheckExistingBooking(eq(2L),eq(start),eq(end),anyLong())).thenReturn(false);

        List<RoomDto> availableRooms = roomService.getAvailableRooms(peopleCount, start, end);

        assertThat(availableRooms.size()).isEqualTo(1);
        assertThat(availableRooms.getFirst().getRoomNumber()).isEqualTo(1002);
    }
}
