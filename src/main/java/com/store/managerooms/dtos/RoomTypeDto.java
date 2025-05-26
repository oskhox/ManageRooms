package com.store.managerooms.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RoomTypeDto {

        private long id;

        @NotEmpty(message = "Ange rumstyp")
        private String name;

        @Min(value = 1, message = "Minst en person måste bokas")
        private int bedCount;

        private int extraBedsAvailable;
    }

