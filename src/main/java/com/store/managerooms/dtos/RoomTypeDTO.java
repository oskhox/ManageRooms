package com.store.managerooms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class RoomTypeDTO {
        private long id;
        private String name;
        private int bedCount;
        private int extraBeds;
        private boolean extraBedsAvailable;
    }

