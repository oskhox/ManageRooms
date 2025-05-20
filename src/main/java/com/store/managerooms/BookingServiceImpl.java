package com.store.managerooms;

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
    public List<DetailedBookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> bookingToDetailedBookingDto(b)).toList();

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
    public DetailedBookingDto saveBooking(DetailedBookingDto detailedBookingDto) {
        Booking booking = detailedBookingDtoToBooking(new Booking(),detailedBookingDto);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingToDetailedBookingDto(savedBooking);

    }

    @Override
    public Booking updateDetailedBooking(Long id, DetailedBookingDto detailedBookingDto) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokning " + id + " finns inte."));
        return bookingRepository.save(detailedBookingDtoToBooking(booking,detailedBookingDto));
    }


    @Override
    public Booking detailedBookingDtoToBooking(Booking booking, DetailedBookingDto detailedBookingDto) {
        booking.setStartDate(detailedBookingDto.getStartDate());
        booking.setEndDate(detailedBookingDto.getEndDate());

        Customer customer = customerService.findByName(detailedBookingDto.getCustomerName())
                .findByAdress(detailedBookingDto.getCustomerAddress())
                .findByEmail(detailedBookingDto.getCustomerEmail());
        booking.setCustomer(customer);

        Room room = roomService.findByRoomNumber(detailedBookingDto.getRoomNumber())
                .findByRoomtype(detailedBookingDto.getRoomType());
        booking.setRoom(room);

        return booking;
    }


    @Override
    public BookingDto bookingToBookingDto(Booking booking) {
        return BookingDto.builder()
                .Id(booking.getId())
                .startDate(booking.getStartDate())
                .endDate(booking.getEndDate())
                .customerName(booking.getCustomer().getName())
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

    @Override
    public Booking bookingDtoToBooking(BookingDto bookingDto) {
        return null;
    }
}