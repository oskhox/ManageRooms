package com.store.managerooms.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Room {

    @Id
    @GeneratedValue
    private long id;
    private int roomNumber;

    @ManyToOne
    @JoinColumn
    private RoomType roomType;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }
}
