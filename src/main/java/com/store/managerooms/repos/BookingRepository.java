package com.store.managerooms.repos;

import com.store.managerooms.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("select (count(b) > 0) " +
            "from Booking b " +
            "where b.room.roomId = :roomId " +
            "and b.startDate < :endDate " +
            "and b.endDate > :startDate")
    Boolean isRoomBookedCheckNewBooking(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);


    @Query("select (count(b) > 0) " +
            "from Booking b " +
            "where b.room.roomId = :roomId " +
            "and b.startDate < :endDate " +
            "and b.endDate > :startDate " +
            "and b.id <> :bookingId")
    Boolean isDateBookedCheckExistingBooking(
            @Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("bookingId") Long bookingId);
}


