package com.store.managerooms.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class DetailedBookingDto {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @Valid
    @NotNull(message = "Rum är obligatoriskt")
    private RoomDto room;

    private DetailedCustomerDto customer;



}

