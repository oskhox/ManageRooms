package com.store.managerooms;

import java.util.List;

public interface BookingService {

    public BookingDto bookingToBookingDto(Booking booking);
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking);
    public Booking bookingDtoToBooking(BookingDto bookingDto);

    public List<DetailedBookingDto> getAllBookings();
    public BookingDto saveBooking(BookingDto bookingDto);
    public DetailedBookingDto findBookingById(Long id);


}
