package com.store.managerooms.repos;

import com.store.managerooms.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long>{
}
