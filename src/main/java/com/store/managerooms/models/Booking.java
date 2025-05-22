package com.store.managerooms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
public class Booking {


    @Id
    @GeneratedValue
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn()
    private Customer customer;
    /*
    @OneToOne
    @JoinColumn()
    private Room room;
*/
}
