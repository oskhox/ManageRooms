package com.store.managerooms;

import java.util.List;

public interface BookingService {

    public BookingDto bookingToBookingDto(Booking booking);
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking);
    public Booking bookingDtoToBooking(BookingDto bookingDto);
    public Booking DetailedBookingDtoToBooking(DetailedBookingDto detailedBookingDto);


        public List<DetailedBookingDto> getAllBookings();
    public BookingDto saveBooking(BookingDto bookingDto);
    public DetailedBookingDto findBookingById(Long id);
    public void deleteBookingById(Long id);
    public Booking updateBooking(Long id, BookingDto bookingDto);
    public Booking updateDetailedBooking(Long id, DetailedBookingDto detailedBookingDto);


    }
