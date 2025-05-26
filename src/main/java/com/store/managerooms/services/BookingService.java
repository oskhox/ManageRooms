package com.store.managerooms.services;

import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.models.Booking;
import com.store.managerooms.models.Customer;
import com.store.managerooms.models.Room;
import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    DetailedBookingDto bookingToDetailedBookingDto(Booking booking);
    MinimalBookingDto bookingToMinimalBookingDto(Booking booking);
    Booking minimalBookingDtoToBooking(Room room, Customer customer, MinimalBookingDto booking);

    List<MinimalBookingDto> getAllBookings();

    boolean isRoomBooked(Long roomId, LocalDate startDate, LocalDate endDate);
    boolean isDateBookedExistingBooking(Long roomId, LocalDate startDate, LocalDate endDate, Long bookingId);


    MinimalBookingDto createNewBooking(MinimalBookingDto booking);
    DetailedBookingDto findDetailedBookingById(Long id);
    MinimalBookingDto findMinimalBookingById(Long id);
    MinimalBookingDto updateExistingBooking(MinimalBookingDto booking);

    void deleteBookingById(Long id);



}
