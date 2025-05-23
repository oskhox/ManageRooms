package com.store.managerooms.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    private Long roomId;
    private int roomNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private RoomType roomType;

    public Room(int roomNumber, RoomType roomType) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
    }
}
