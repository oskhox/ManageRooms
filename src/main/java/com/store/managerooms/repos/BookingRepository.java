package com.store.managerooms.Repos;

import com.store.managerooms.Models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long>{
}
