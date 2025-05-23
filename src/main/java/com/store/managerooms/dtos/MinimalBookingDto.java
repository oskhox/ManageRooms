package com.store.managerooms.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class MinimalBookingDto {

    private Long id;

    @NotNull(message = "Startdatum är obligatoriskt")
    private LocalDate startDate;

    @NotNull(message = "Slutdatum är obligatoriskt")
    private LocalDate endDate;

    @Valid
    @NotNull(message = "Rum är obligatoriskt")
    private RoomDto room;

}
