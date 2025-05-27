package com.store.managerooms.RoomTest;

import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.RoomRepo;
import com.store.managerooms.repos.RoomTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
public class RoomServiceImplTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private RoomTypeRepo roomTypeRepo;


}