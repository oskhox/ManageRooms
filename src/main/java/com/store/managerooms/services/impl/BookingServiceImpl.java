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

    @Override
    public DetailedBookingDto findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokningen hittas ej"));
        return bookingToDetailedBookingDto(booking);
    }

    public boolean isRoomBooked(Long roomId, LocalDate startDate, LocalDate endDate) {
            return bookingRepository.isRoomBookedCheck(roomId,startDate,endDate);
    }


    @Override
    public void saveNewBooking(DetailedBookingDto booking) {
         boolean isRoomBooked = isRoomBooked(booking.getRoomId(), booking.getStartDate(), booking.getEndDate());
         if (isRoomBooked) {
                throw new IllegalStateException("Rummet är redan bokat för önskat datum.");
            }

        Customer customer = customerService.findByCustomerId(booking.getCustomerId());
        Room room = roomService.findByRoomId(booking.getRoomId());

        bookingRepository.save(detailedBookingDtoToBooking(customer,room,booking));
    }

    @Override
    public void updateExistingBooking(MinimalBookingDto booking) {
        Booking existingBooking = bookingRepository.findById(booking.getId())
                .orElseThrow(() -> new NoSuchElementException("Bokningen hittas ej"));

        boolean isRoomBooked = isRoomBooked(booking.getRoomId(), booking.getStartDate(), booking.getEndDate());
        if (isRoomBooked) {
            throw new IllegalStateException("Rummet är redan bokat för önskat datum.");
        }

        if (booking.getStartDate() != null) {
            existingBooking.setStartDate(booking.getStartDate());
        }

        if (booking.getEndDate() != null) {
            existingBooking.setEndDate(booking.getEndDate());
        }

        if (booking.getRoomId() != null) {
            Room room = roomService.findByRoomId(booking.getRoomId());
            existingBooking.setRoom(room);
        }

        bookingRepository.save(existingBooking);
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
                .room(new MinimalRoomDto(
                        booking.getRoom().getId(),
                        booking.getRoom().getRoomNumber()))
                .build();
    }
}