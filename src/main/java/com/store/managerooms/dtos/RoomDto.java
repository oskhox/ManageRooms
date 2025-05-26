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

    @NotNull(message = "Rummets id krävs")
    private Long id;

    @NotNull(message = "Rummets rumsnummer krävs")
    private int roomNumber;

    @Valid
    private RoomTypeDto roomType;
}

