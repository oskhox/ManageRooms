/*
package com.store.managerooms.services.impl;

import com.store.managerooms.dtos.DetailedCustomerDto;
import com.store.managerooms.models.Booking;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.dtos.DetailedBookingDto;
import com.store.managerooms.dtos.MinimalBookingDto;
import com.store.managerooms.models.Customer;
import com.store.managerooms.services.CustomerService;
import com.store.managerooms.services.BookingService;
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
    public List<MinimalBookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> bookingToBookingDto(b)).toList();
    }

    @Override
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public DetailedBookingDto findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokning " + id + " finns inte."));
        return bookingToDetailedBookingDto(booking);
    }


    @Override
    public void saveNewBooking(DetailedBookingDto booking) {
        Customer customer = customerService.findByCustomerId(booking.getCustomerId());
        Room room = roomService.findById(booking.getRoomId());

        bookingRepository.save(detailedBookingDtoToBooking(customer,room,booking));
    }

    @Override
    public void updateExistingBooking(DetailedBookingDto booking) {
        Booking existingBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new NoSuchElementException("Bokning " + booking.getId() + " finns inte."));

        Customer customer = customerService.findByCustomerId(booking.getCustomerId());
        Room room = roomService.findById(booking.getRoomId());

        existingBooking.setStartDate(booking.getStartDate());
        existingBooking.setEndDate(booking.getEndDate());
        existingBooking.setCustomer(customer);
        existingBooking.setRoom(room);
        bookingRepository.save(existingBooking);
    }

    @Override
    public Booking detailedBookingDtoToBooking(Customer customer, Room room, DetailedBookingDto detailedBookingDto) {
        return Booking.builder()
                .startDate(detailedBookingDto.getStartDate())
                .endDate(detailedBookingDto.getEndDate())
                .customer(customer)
                .room(room)
                .build();
    }


    @Override
    public Booking bookingDtoToBooking(Room room, MinimalBookingDto minimalBookingDTO) {
        return Booking.builder()
                .startDate(minimalBookingDTO.getStartDate())
                .endDate(minimalBookingDTO.getEndDate())
                .room(room)
                .build();
    }

    @Override
    public MinimalBookingDto bookingToBookingDto(Booking booking) {
        return MinimalBookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .roomNumber(booking.getRoom().getRoomNumber())
                .build();
    }

    @Override
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking) {
        return DetailedBookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .customer(new DetailedCustomerDto(
                        booking.getCustomer().getId(),
                        booking.getCustomer().getFirstName(),
                        booking.getCustomer().getLastName(),
                        booking.getCustomer().getPhone(),
                        booking.getCustomer().getEmail()))
                .room(new MinimalRoomDTO(
                        booking.getRoom().getId(),
                        booking.getRoom().getRoomNumber()))
                .build();
    }
}
*/

