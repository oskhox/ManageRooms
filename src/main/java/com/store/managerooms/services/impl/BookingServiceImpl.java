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
    public DetailedBookingDto saveNewBooking(DetailedBookingDto detailedBookingDto) {
        Customer customer = customerService.findById(detailedBookingDto.getCustomerId());
        Room room = roomService.findById(detailedBookingDto.getRoomId());

        Booking booking = detailedBookingDtoToBooking(customer, room, detailedBookingDto);
        Booking savedBooking = bookingRepository.save(booking);

        return bookingToDetailedBookingDto(savedBooking);
    }

    @Override
    public Booking updateExistingBooking(Long id, DetailedBookingDto detailedBookingDto) {
        bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokning " + id + " finns inte."));

        Customer customer = customerService.findById(detailedBookingDto.getCustomerId());
        Room room = roomService.findById(detailedBookingDto.getRoomId());

        Booking updatedBooking = detailedBookingDtoToBooking(customer,room,detailedBookingDto);
        return bookingRepository.save(updatedBooking);
    }

    @Override
    public Booking detailedBookingDtoToBooking(Customer customer, Room room, DetailedBookingDto dto) {
        return Booking.builder()
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .customer(customer)
                .room(room)
                .build();
    }


    @Override
    public Booking bookingDtoToBooking((Customer customer, Room room, BookingDto bookingDto) {
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
                .customerName(booking.getCustomer().getName())
                .customerAddress(booking.getCustomer().getAddress())
                .customerEmail(booking.getCustomer().getEmail())
                .roomNumber(booking.getRoom().getRoomNumber())
                .roomType(booking.getRoom().getRoomType().getName())
                .build();
    }

}