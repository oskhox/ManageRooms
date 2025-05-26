package com.store.managerooms.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
    public class RoomDto {

    private Long id;

    @NotNull(message = "Välj rumsnummer")
    private int roomNumber;

    @Valid
    private RoomTypeDto roomType;
}

