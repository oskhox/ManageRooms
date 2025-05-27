package com.store.managerooms.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RoomTypeDto {
        @NotNull
        private long id;

        @NotNull
        private String name;

        @NotNull
        private int bedCount;

        private int extraBedsAvailable;
    }

