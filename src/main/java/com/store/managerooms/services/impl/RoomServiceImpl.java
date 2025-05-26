package com.store.managerooms.services.impl;
import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.dtos.RoomTypeDto;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.BookingRepository;
import com.store.managerooms.repos.RoomRepo;
import com.store.managerooms.repos.RoomTypeRepo;
import com.store.managerooms.services.RoomService;
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


    public RoomServiceImpl(RoomRepo roomRepo, BookingRepository bookingRepo, RoomTypeRepo roomTypeRepo)
    {
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

    public Room findByRoomId(Long id) {
        return roomRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
    }

    @Override
    public List<Room> getAvailableRooms(int peopleCount, LocalDate start, LocalDate end) {
        List<Room> availableRooms = new ArrayList<>();
        long idCounter = 1;
        for (Room room : roomRepo.findAll()) {
            if (bookingRepo.isDateBookedCheckExistingBooking(room.getRoomId(), start, end, idCounter)) {
                System.out.println("Room with id " + room.getRoomId() + " is already booked");
            } else {
                int peopleInRoom = room.getRoomType().getBedCount() + room.getRoomType().getExtraBedsAvailable();
                if (peopleInRoom >= peopleCount) {
                    availableRooms.add(room);
                }
            }
            idCounter++;
        }
        return availableRooms;
    }

    @Override
    public String addBeds(@RequestParam Long roomTypeId, @RequestParam int beds){
        RoomType roomType = roomTypeRepo.findById(roomTypeId).get();
        int availableBeds = roomType.getExtraBedsAvailable();

        if (availableBeds != 0 && availableBeds <= beds) {
            roomType.setBedCount(roomType.getBedCount() + beds);
            roomType.setExtraBedsAvailable(availableBeds - beds);
            roomTypeRepo.save(roomType);
            return "index";
//            return "Added " + beds + " beds " + " to " + roomType.getName() + " with id: " + roomTypeId;
        }
        else if(roomType.getName().equals("Single room")){
            return "This is a Single room, you cant add any extra beds to this room";
        }
        else {
            return "There is " + roomType.getExtraBedsAvailable() + " extra beds available";
        }
    }
}