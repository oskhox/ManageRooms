package com.store.managerooms.RoomTest;


import com.store.managerooms.controllers.RoomController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RoomControllerCanBeCreated {

    @Autowired
    private RoomController roomController;

    @Test
    void contextLoads() throws Exception {
        assertThat(roomController).isNotNull();
    }

}