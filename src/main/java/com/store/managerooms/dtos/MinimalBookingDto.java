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

    private LocalDate startDate;

    private LocalDate endDate;


    private RoomDto room;

}
