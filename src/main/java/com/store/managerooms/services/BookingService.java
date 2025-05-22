package com.store.managerooms.services;

import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.models.Booking;
import com.store.managerooms.models.Customer;

import java.util.List;

public interface BookingService {

    public MinimalBookingDto bookingToBookingDto(Booking booking);
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking);

    public Booking bookingDtoToBooking(Room room, MinimalBookingDto minimalBookingDTO);
    public Booking detailedBookingDtoToBooking(Customer customer, Room room, DetailedBookingDto detailedBookingDto);

    public List<MinimalBookingDto> getAllBookings();

    public void saveNewBooking(DetailedBookingDto detailedBookingDto);

    public DetailedBookingDto findBookingById(Long id);

    public void deleteBookingById(Long id);

    public void updateExistingBooking(DetailedBookingDto detailedBookingDto);


    }
