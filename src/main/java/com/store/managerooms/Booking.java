package com.store.managerooms;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn()
    private Customer customer;

    @ManyToOne
    @JoinColumn()
    private Room room;

}
