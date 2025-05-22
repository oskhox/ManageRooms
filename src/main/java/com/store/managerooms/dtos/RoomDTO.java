package com.store.managerooms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RoomDTO {

        private long id;
        private int roomNumber;
        private RoomTypeDTO roomType;

    }

