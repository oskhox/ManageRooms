package com.store.managerooms.Services;

import com.store.managerooms.DTOs.DetailedBookingDTO;
import com.store.managerooms.DTOs.MinimalBookingDTO;
import com.store.managerooms.Models.Booking;
import com.store.managerooms.Models.Customer;

import java.util.List;

public interface BookingService {

    public MinimalBookingDTO bookingToBookingDto(Booking booking);
    public DetailedBookingDTO bookingToDetailedBookingDto(Booking booking);

    public Booking bookingDtoToBooking(Room room, MinimalBookingDTO minimalBookingDTO);
    public Booking detailedBookingDtoToBooking(Customer customer, Room room, DetailedBookingDTO detailedBookingDto);
*/
    public List<MinimalBookingDTO> getAllBookings();

    public void saveNewBooking(DetailedBookingDTO detailedBookingDto);

    public DetailedBookingDTO findBookingById(Long id);

    public void deleteBookingById(Long id);

    public void updateExistingBooking(DetailedBookingDTO detailedBookingDto);


    }
