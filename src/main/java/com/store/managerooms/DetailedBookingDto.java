package com.store.managerooms;

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

    private String customerName;
    private String customerAddress;
    private String customerEmail;

    private String roomNumber;
    private String roomType;

}

