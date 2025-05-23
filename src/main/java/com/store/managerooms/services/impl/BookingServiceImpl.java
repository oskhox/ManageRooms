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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    @Autowired
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
        Long roomId = booking.getRoom().getId();
        Set<Long> customerIds = booking.getCustomers().stream().map(DetailedCustomerDto::getId).collect(Collectors.toSet());

         boolean isRoomBooked = isRoomBooked(roomId,
                 booking.getStartDate(), booking.getEndDate());
         if (isRoomBooked) {
                throw new IllegalStateException("Rummet är redan bokat för önskat datum.");
            }

        Set<Customer> customers = customerIds.stream()
                .map(customerService::findByCustomerId)
                .collect(Collectors.toSet());        Room room = roomService.findByRoomId(roomId);

        Booking newBooking = detailedBookingDtoToBooking(customers, room, booking);
        Booking savedBooking = bookingRepository.save(newBooking);

        return bookingToDetailedBookingDto(savedBooking);

    }

    @Override
    public MinimalBookingDto updateExistingBooking(MinimalBookingDto booking) {
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
    public DetailedBookingDto findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokningen hittas ej"));
        return bookingToDetailedBookingDto(booking);
    }

    @Override
    public Booking detailedBookingDtoToBooking(Set<Customer> customers, Room room, DetailedBookingDto booking) {
        return Booking.builder()
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .customers(customers)
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
                roomType.getExtraBeds(),
                roomType.isExtraBedsAvailable());
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
                roomType.getExtraBeds(),
                roomType.isExtraBedsAvailable());
        RoomDto roomDto = new RoomDto(
                room.getRoomId(),
                room.getRoomNumber(),
                roomTypeDto);
        Set<DetailedCustomerDto> customerDtos = booking.getCustomers()
                .stream().map(c -> new DetailedCustomerDto(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getPhone(),
                c.getEmail()))
                .collect(Collectors.toSet());

        return DetailedBookingDto.builder()
                .id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .customers(customerDtos)
                .room(roomDto)
                .build();
    }
}