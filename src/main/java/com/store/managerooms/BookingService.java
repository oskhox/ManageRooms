package com.store.managerooms;



import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void deleteBookingById(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking findBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Bokningen med id " + id + " finns inte."));
    }


    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

}
