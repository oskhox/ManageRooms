package com.store.managerooms;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;
    private final CustomerService customerService;
    private final RoomService roomService;

    public List<DetailedBookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(b -> bookingToDetailedBookingDto(b)).toList();

}

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    public DetailedBookingDto findBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokning " + id + " finns inte."));
        return bookingToDetailedBookingDto(booking);
    }


    public BookingDto saveBooking(BookingDto bookingDto) {
        Booking booking = bookingDtoToBooking(bookingDto);
        Booking savedBooking = bookingRepository.save(booking);
        return bookingToBookingDto(savedBooking);

    }

    public Booking bookingDtoToBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setId(bookingDto.getId());
        booking.setStartDate(bookingDto.getStartDate());
        booking.setEndDate(bookingDto.getEndDate());

        Customer customer = customerService.findByName(bookingDto.getCustomerName());
        booking.setCustomer(customer);

        Room room = roomService.findByRoomNumber(bookingDto.getRoomNumber());
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
