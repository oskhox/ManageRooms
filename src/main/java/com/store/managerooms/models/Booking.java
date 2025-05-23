package com.store.managerooms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "booking")
public class Booking {


    @Id
    @GeneratedValue
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "booking")
    private Set<Customer> customers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;
}
