package com.store.managerooms.dtos;

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
    //private MinimalRoomDTO room;

}
