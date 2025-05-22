package com.store.managerooms.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class RoomType {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int bedCount;
    private int extraBeds;
    private boolean extraBedsAvailable;

    public RoomType(String name, int bedCount) {
        this.name = name;
        this.bedCount = bedCount;
    }

    public RoomType(String name, int bedCount, int extraBeds, boolean extraBedsAvailable) {
        this.name = name;
        this.bedCount = bedCount;
        this.extraBeds = extraBeds;
        this.extraBedsAvailable = extraBedsAvailable;
    }
}
