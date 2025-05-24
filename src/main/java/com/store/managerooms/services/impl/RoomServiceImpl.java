package com.store.managerooms.services.impl;
import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.dtos.RoomTypeDto;
import com.store.managerooms.models.Room;
import com.store.managerooms.models.RoomType;
import com.store.managerooms.repos.RoomRepo;
import com.store.managerooms.services.RoomService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

        @Service
        public class RoomServiceImpl implements RoomService {

            private final RoomRepo roomRepository;

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


            public RoomServiceImpl(RoomRepo roomRepository) {
                this.roomRepository = roomRepository;
            }

            @Override
            public List<RoomDto> getAllRooms() {
                return roomRepository.findAll().stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
            }

            @Override
            public RoomDto getRoomById(long id) {
                return roomRepository.findById(id)
                        .map(this::convertToDTO)
                        .orElse(null);
            }

            public Room findByRoomId(Long id) {
                return roomRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
            }



}
