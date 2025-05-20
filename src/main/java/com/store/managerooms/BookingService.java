package com.store.managerooms;

import java.util.List;
import java.util.NoSuchElementException;

public interface BookingService {

    public BookingDto bookingToBookingDto(Booking booking);
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking);

    public List<DetailedBookingDto> getAllBookings();
    public BookingDto saveBooking(BookingDto bookingDto);


}
