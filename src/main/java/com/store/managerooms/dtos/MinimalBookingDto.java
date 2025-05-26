package com.store.managerooms.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
    @NotNull(message = "Startdatum krävs")
    private LocalDate startDate;

    @NotNull(message = "Slutdatum krävs")
    private LocalDate endDate;

    @Valid
    private RoomDto room;

    @Valid
    private MinimalCustomerDto customer;

}
