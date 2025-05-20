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
    private int bedCounts;
    private int addBeds;
    private boolean extraBeds;

    public RoomType(String name, int bedCounts) {
        this.name = name;
        this.bedCounts = bedCounts;
        this.extraBeds = extraBeds;
    }

    public RoomType(String name, int bedCounts, int addBeds, boolean extraBeds) {
        this.name = name;
        this.bedCounts = bedCounts;
        this.addBeds = addBeds;
        this.extraBeds = extraBeds;
    }
}
