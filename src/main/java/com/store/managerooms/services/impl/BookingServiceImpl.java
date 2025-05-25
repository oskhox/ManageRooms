package com.store.managerooms.services.impl;

import com.store.managerooms.dtos.*;
import com.store.managerooms.models.Booking;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.models.Customer;
import com.store.managerooms.services.CustomerService;
import com.store.managerooms.services.BookingService;
import com.store.managerooms.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                .map(b -> bookingToMinimalBookingDto(b)).toList();
    }

    @Override
    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }



    public boolean isRoomBooked(Long roomId, LocalDate startDate, LocalDate endDate) {
            return bookingRepository.isRoomBookedCheckNewBooking(roomId,startDate,endDate);
    }

    public boolean isDateBookedExistingBooking(Long roomId, LocalDate startDate, LocalDate endDate, Long bookingId) {
        return bookingRepository.isDateBookedCheckExistingBooking(roomId,startDate,endDate,bookingId);
    }


    @Override
    public DetailedBookingDto createNewBooking(DetailedBookingDto booking) {
        if (booking.getEndDate().isBefore(booking.getStartDate())) {
            throw new IllegalArgumentException("Slutdatum får inte vara före startdatum.");
        }

        Long roomId = booking.getRoom().getId();
        Long customerId = booking.getCustomer().getId();

         boolean isRoomBooked = isRoomBooked(roomId,
                 booking.getStartDate(), booking.getEndDate());
         if (isRoomBooked) {
                throw new IllegalStateException("Rummet är redan bokat för önskat datum.");
            }

        Customer customer = customerService.findByCustomerId(customerId);
        Room room = roomService.findByRoomId(roomId);

        Booking newBooking = detailedBookingDtoToBooking(customer, room, booking);
        Booking savedBooking = bookingRepository.save(newBooking);

        return bookingToDetailedBookingDto(savedBooking);

    }

    @Override
    public MinimalBookingDto updateExistingBooking(MinimalBookingDto booking) {
        if (booking.getEndDate().isBefore(booking.getStartDate())) {
            throw new IllegalArgumentException("Slutdatum får inte vara före startdatum.");
        }

        Booking existingBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new NoSuchElementException("Bokningen hittas ej"));


        Long roomId = booking.getRoom().getId();

        boolean isRoomBooked = isDateBookedExistingBooking(roomId,
                booking.getStartDate(), booking.getEndDate(),booking.getId());
        if (isRoomBooked) {
            throw new IllegalStateException("Rummet är redan bokat för önskat datum.");
        }

        Room room = roomService.findByRoomId(roomId);

            existingBooking.setStartDate(booking.getStartDate());
            existingBooking.setEndDate(booking.getEndDate());
            existingBooking.setRoom(room);

        bookingRepository.save(existingBooking);
        return bookingToMinimalBookingDto(existingBooking);

    }

    @Override
    public DetailedBookingDto findDetailedBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokningen hittas ej"));
        return bookingToDetailedBookingDto(booking);
    }

    @Override
    public MinimalBookingDto findMinimalBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokningen hittas ej"));
        return bookingToMinimalBookingDto(booking);
    }

    @Override
    public Booking detailedBookingDtoToBooking(Customer customer, Room room, DetailedBookingDto booking) {
        return Booking.builder()
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .customer(customer)
                .room(room)
                .build();
    }


    @Override
    public Booking minimalBookingDtoToBooking(Room room, MinimalBookingDto booking) {
        return Booking.builder()
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .room(room)
                .build();
    }

    @Override
    public MinimalBookingDto bookingToMinimalBookingDto(Booking booking) {
        Room room = booking.getRoom();
        RoomType roomType = room.getRoomType();

        RoomTypeDto roomTypeDto = new RoomTypeDto(
                roomType.getId(),
                roomType.getName(),
                roomType.getBedCount(),
                roomType.getExtraBedsAvailable());
        RoomDto roomDto = new RoomDto(
                room.getRoomId(),
                room.getRoomNumber(),
                roomTypeDto);

        return MinimalBookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .room(roomDto)
                .build();
    }


    @Override
    public DetailedBookingDto bookingToDetailedBookingDto(Booking booking) {
        Room room = booking.getRoom();
        RoomType roomType = room.getRoomType();

        RoomTypeDto roomTypeDto = new RoomTypeDto(
                roomType.getId(),
                roomType.getName(),
                roomType.getBedCount(),
                roomType.getExtraBedsAvailable());
        RoomDto roomDto = new RoomDto(
                room.getRoomId(),
                room.getRoomNumber(),
                roomTypeDto);
        DetailedCustomerDto customerDto = new DetailedCustomerDto(
                booking.getCustomer().getId(),
                booking.getCustomer().getFirstName(),
                booking.getCustomer().getLastName(),
                booking.getCustomer().getPhone(),
                booking.getCustomer().getEmail());

        return DetailedBookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .customer(customerDto)
                .room(roomDto)
                .build();
    }
}