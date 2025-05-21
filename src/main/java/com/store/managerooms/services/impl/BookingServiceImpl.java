package com.store.managerooms.Services.impl;

import com.store.managerooms.DTOs.DetailedCustomerDTO;
import com.store.managerooms.DTOs.MinimalCustomerDTO;
import com.store.managerooms.Models.Booking;
import com.store.managerooms.Repos.BookingRepository;
import com.store.managerooms.DTOs.DetailedBookingDTO;
import com.store.managerooms.DTOs.MinimalBookingDTO;
import com.store.managerooms.Models.Customer;
import com.store.managerooms.Services.CustomerService;
import com.store.managerooms.Services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerService customerService;
    private final RoomService roomService;

    @Override
    public List<MinimalBookingDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> bookingToBookingDto(b)).toList();
    }

    @Override
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public DetailedBookingDTO findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokning " + id + " finns inte."));
        return bookingToDetailedBookingDto(booking);
    }


    @Override
    public void saveNewBooking(DetailedBookingDTO booking) {
        Customer customer = customerService.findById(booking.getCustomerId());
        Room room = roomService.findById(booking.getRoomId());

        bookingRepository.save(detailedBookingDtoToBooking(customer,room,booking));
    }

    @Override
    public void updateExistingBooking(DetailedBookingDTO booking) {
        Booking existingBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new NoSuchElementException("Bokning " + booking.getId() + " finns inte."));

        Customer customer = customerService.findById(booking.getCustomerId());
        Room room = roomService.findById(booking.getRoomId());

        existingBooking.setStartDate(booking.getStartDate());
        existingBooking.setEndDate(booking.getEndDate());
        existingBooking.setCustomer(customer);
        existingBooking.setRoom(room);
        bookingRepository.save(existingBooking);
    }

    @Override
    public Booking detailedBookingDtoToBooking(Customer customer, Room room, DetailedBookingDTO detailedBookingDto) {
        return Booking.builder()
                .startDate(detailedBookingDto.getStartDate())
                .endDate(detailedBookingDto.getEndDate())
                .customer(customer)
                .room(room)
                .build();
    }


    @Override
    public Booking bookingDtoToBooking(Room room, MinimalBookingDTO minimalBookingDTO) {
        return Booking.builder()
                .startDate(minimalBookingDTO.getStartDate())
                .endDate(minimalBookingDTO.getEndDate())
                .room(room)
                .build();
    }

    @Override
    public MinimalBookingDTO bookingToBookingDto(Booking booking) {
        return MinimalBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .roomNumber(booking.getRoom().getRoomNumber())
                .build();
    }

    @Override
    public DetailedBookingDTO bookingToDetailedBookingDto(Booking booking) {
        return DetailedBookingDTO.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .customer(new DetailedCustomerDTO(
                        booking.getCustomer().getId(),
                        booking.getCustomer().getFirstName(),
                        booking.getCustomer().getLastName(),
                        booking.getCustomer().getPhone(),
                        booking.getCustomer().getEmail()))
                .room(new RoomDto(
                        booking.getRoom().getId(),
                        booking.getRoom().getRoom(),
                        booking.getRoom().getRoomNumber()))
                .build();
    }
}


