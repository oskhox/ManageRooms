package com.store.managerooms.services;

import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.models.Booking;
import com.store.managerooms.models.Customer;
import com.store.managerooms.models.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingService {

    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking);
    public MinimalBookingDto bookingToMinimalBookingDto(Booking booking);

    public Booking minimalBookingDtoToBooking(Room room, MinimalBookingDto minimalBookingDTO);
    public Booking detailedBookingDtoToBooking(Set<Customer> customers, Room room, DetailedBookingDto booking);

    public List<MinimalBookingDto> getAllBookings();

    public boolean isRoomBooked(Long roomId, LocalDate startDate, LocalDate endDate);

    public DetailedBookingDto createNewBooking(DetailedBookingDto booking);

    public DetailedBookingDto findBookingById(Long id);

    public void deleteBookingById(Long id);

    public MinimalBookingDto updateExistingBooking(MinimalBookingDto booking);


    }
