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

public class DetailedBookingDto {

    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private CustomerDto customer;
    private RoomDto room;

}

