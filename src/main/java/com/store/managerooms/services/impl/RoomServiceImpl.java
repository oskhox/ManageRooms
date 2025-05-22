package com.store.managerooms.services.impl;
import com.store.managerooms.dtos.RoomDTO;
import com.store.managerooms.dtos.RoomTypeDTO;
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

            private RoomDTO convertToDTO(Room room) {
                RoomType rt = room.getRoomType();
                RoomTypeDTO rtDTO = new RoomTypeDTO(
                        rt.getId(),
                        rt.getName(),
                        rt.getBedCount(),
                        rt.getExtraBeds(),
                        rt.isExtraBedsAvailable()
                );

                return new RoomDTO(room.getId(), room.getRoomNumber(), rtDTO);
            }


            public RoomServiceImpl(RoomRepo roomRepository) {
                this.roomRepository = roomRepository;
            }

            @Override
            public List<RoomDTO> getAllRooms() {
                return roomRepository.findAll().stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList());
            }

            @Override
            public RoomDTO getRoomById(long id) {
                return roomRepository.findById(id)
                        .map(this::convertToDTO)
                        .orElse(null);
            }

        }

