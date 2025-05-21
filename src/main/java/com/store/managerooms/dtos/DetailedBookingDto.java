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

public class DetailedBookingDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private DetailedCustomerDTO customer;
    private RoomDTO room;

}

