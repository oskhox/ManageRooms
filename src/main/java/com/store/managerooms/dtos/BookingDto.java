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

public class BookingDto {

    private Long Id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String customerName;
    private String roomNumber;
}
