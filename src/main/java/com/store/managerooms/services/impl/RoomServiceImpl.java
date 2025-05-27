package com.store.managerooms.services.impl;
import com.store.managerooms.dtos.AddBedsDto;
import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.dtos.RoomTypeDto;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.RoomRepo;
import com.store.managerooms.repos.RoomTypeRepo;
import com.store.managerooms.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

        @Service
        public class RoomServiceImpl implements RoomService {

            private final RoomRepo roomRepo;
            private final BookingRepository bookingRepo;
            private final RoomTypeRepo roomTypeRepo;

            private RoomDto convertToDTO(Room room) {
                RoomType rt = room.getRoomType();
                RoomTypeDto rtDTO = new RoomTypeDto(
                        rt.getId(),
                        rt.getName(),
                        rt.getBedCount(),
                        rt.getExtraBedsAvailable()
                );

                return new RoomDto(room.getRoomId(), room.getRoomNumber(), rtDTO);
            }


            public RoomServiceImpl(RoomRepo roomRepo, BookingRepository bookingRepo, RoomTypeRepo roomTypeRepo) {
                this.roomRepo = roomRepo;
                this.bookingRepo = bookingRepo;
                this.roomTypeRepo = roomTypeRepo;
            }

            @Override
            public List<RoomDto> getAllRooms() {
                return roomRepo.findAll().stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
            }

            public RoomDto findByRoomId(Long id) {
                Room room = roomRepo.findById(id)
                        .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));

                return convertToDTO(room);
            }

            @Override
            public List<RoomDto> getAvailableRooms(@Valid int peopleCount, LocalDate start, LocalDate end) {
                List<RoomDto> availableRooms = new ArrayList<>();
                long idCounter = 1;
                for (Room room : roomRepo.findAll()) {
                    boolean isBooked = bookingRepo.isDateBookedCheckExistingBooking(room.getRoomId(), start, end, idCounter);
                    if (!isBooked) {
                        int peopleInRoom = room.getRoomType().getBedCount() + room.getRoomType().getExtraBedsAvailable();
                        if (peopleInRoom >= peopleCount) {
                            availableRooms.add(convertToDTO(room));
                        }
                    }
                    idCounter++;
                    }

                return availableRooms;
            }

            @Override
            public void addBeds(@Valid AddBedsDto addBeds) {
                RoomType roomType = roomTypeRepo.findById(addBeds.getRoomTypeId())
                        .orElseThrow(() -> new RuntimeException("Rummet hittades inte"));

                int availableBeds = roomType.getExtraBedsAvailable();

                if (availableBeds != 0 && availableBeds >= addBeds.getBeds()) {
                    roomType.setBedCount(roomType.getBedCount() + addBeds.getBeds());
                    roomType.setExtraBedsAvailable(availableBeds - addBeds.getBeds());
                    roomTypeRepo.save(roomType);
                }
            }
        }
