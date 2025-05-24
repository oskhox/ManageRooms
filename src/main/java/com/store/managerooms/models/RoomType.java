package com.store.managerooms.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "roomtype")
public class RoomType {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private int bedCount;
    private int extraBedsAvailable;

    public RoomType(String name, int bedCount) {
        this.name = name;
        this.bedCount = bedCount;
    }

    public RoomType(String name, int bedCount, int extraBedsAvailable) {
        this.name = name;
        this.bedCount = bedCount;
        this.extraBedsAvailable = extraBedsAvailable;
    }
}
