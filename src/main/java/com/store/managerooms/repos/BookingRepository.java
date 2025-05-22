package com.store.managerooms.repos;

import com.store.managerooms.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long>{


//    @Query ("select count (Booking) > 0 " +
//            "from Booking " +
//            "where Booking.room.id= :roomId " +
//            "and Booking.startDate < :endDate " +
//            "and Booking.endDate > :startDate")
//    public Boolean isRoomBookedCheck(Long roomdId, LocalDate startDate,LocalDate endDate);
}
