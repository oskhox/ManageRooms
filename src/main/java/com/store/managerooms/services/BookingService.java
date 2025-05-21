package com.store.managerooms.services;

import com.store.managerooms.Booking;
import com.store.managerooms.dtos.BookingDto;
import com.store.managerooms.dtos.DetailedBookingDto;

import java.util.List;

public interface BookingService {

    public BookingDto bookingToBookingDto(Booking booking);
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking);

    public Booking bookingDtoToBooking(Room room, BookingDto bookingDto);
    public Booking detailedBookingDtoToBooking(Customer customer, Room room, DetailedBookingDto detailedBookingDto);


    public List<BookingDto> getAllBookings();

    public void saveNewBooking(DetailedBookingDto detailedBookingDto);

    public DetailedBookingDto findBookingById(Long id);

    public void deleteBookingById(Long id);

    public void updateExistingBooking(DetailedBookingDto detailedBookingDto);


    }
