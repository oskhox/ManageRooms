package com.store.managerooms.services;

import com.store.managerooms.Booking;
import com.store.managerooms.dtos.BookingDto;
import com.store.managerooms.dtos.DetailedBookingDto;

import java.util.List;

public interface BookingService {

    public BookingDto bookingToBookingDto(Booking booking);
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking);
    public Booking bookingDtoToBooking(BookingDto bookingDto);
    public Booking detailedBookingDtoToBooking(Booking booking, DetailedBookingDto detailedBookingDto);


    public List<DetailedBookingDto> getAllBookings();
    public DetailedBookingDto saveBooking(DetailedBookingDto detailedBookingDto);
    public DetailedBookingDto findBookingById(Long id);
    public void deleteBookingById(Long id);
    public Booking updateDetailedBooking(Long id, DetailedBookingDto detailedBookingDto);


    }
