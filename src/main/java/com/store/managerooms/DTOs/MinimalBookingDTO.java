package com.store.managerooms.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class MinimalBookingDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private MinimalRoomDTO room;

}
