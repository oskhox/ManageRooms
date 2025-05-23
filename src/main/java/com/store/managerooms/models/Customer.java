package com.store.managerooms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
/*
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

 */
}