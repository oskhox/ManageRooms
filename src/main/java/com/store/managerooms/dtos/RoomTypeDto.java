package com.store.managerooms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RoomTypeDto {
        private long id;
        private String name;
        private int bedCount;
        private int extraBedsAvailable;
    }

