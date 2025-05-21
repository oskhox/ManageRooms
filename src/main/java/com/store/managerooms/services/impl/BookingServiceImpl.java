package com.store.managerooms.services.impl;

import com.store.managerooms.Booking;
import com.store.managerooms.dtos.BookingDto;
import com.store.managerooms.BookingRepository;
import com.store.managerooms.dtos.DetailedBookingDto;
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
    public List<BookingDto> getAllBookings() {
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
        Customer customer = customerService.findById(booking.getCustomerId());
        Room room = roomService.findById(booking.getRoomId());

        bookingRepository.save(detailedBookingDtoToBooking(customer,room,booking));
    }

    @Override
    public void updateExistingBooking(Long id, DetailedBookingDto booking) {
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokning " + id + " finns inte."));

        Customer customer = customerService.findById(booking.getCustomerId());
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
    public Booking bookingDtoToBooking(Room room, BookingDto bookingDto) {
        return Booking.builder()
                .startDate(bookingDto.getStartDate())
                .endDate(bookingDto.getEndDate())
                .room(room)
                .build();
    }

    @Override
    public BookingDto bookingToBookingDto(Booking booking) {
        return BookingDto.builder()
                .Id(booking.getId())
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
                .customer(new CustomerDto(
                        booking.getCustomer().getId(),
                        booking.getCustomer().getName(),
                        booking.getCustomer().getAdress(),
                        booking.getCustomer().getEmail()))
                .room(new RoomDto(
                        booking.getRoom().getId(),
                        booking.getRoom().getRoom(),
                        booking.getRoom().getRoomNumber()))
                .build();
    }

}