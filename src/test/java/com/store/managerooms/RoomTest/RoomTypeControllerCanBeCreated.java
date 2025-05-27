package com.store.managerooms.RoomTest;

import com.store.managerooms.controllers.RoomTypeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class RoomTypeControllerCanBeCreated {
    @Autowired
    private RoomTypeController roomTypeController;

    @Test
    void contextLoads() throws Exception {
        assertThat(roomTypeController).isNotNull();
    }
}
