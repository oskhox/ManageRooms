package com.store.managerooms.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private String type;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
    }
}
