package com.store.managerooms.RoomTest;


import com.store.managerooms.dtos.RoomDto;
import com.store.managerooms.dtos.RoomTypeDto;
import com.store.managerooms.services.RoomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerTest {


    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @SuppressWarnings("removal")
    @MockBean
    private RoomService roomService;

    @Test
    void testGetAllRooms() {
        List<RoomDto> mockRooms = List.of(new RoomDto((long)1,1001,new RoomTypeDto(1,"Double Room",2,2)),
                new RoomDto((long)2,1002,new RoomTypeDto(2,"Single Room",1,0)));

        when(roomService.getAllRooms()).thenReturn(mockRooms);

        ResponseEntity<RoomDto[]> response = restTemplate.getForEntity("/rooms", RoomDto[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).length).isEqualTo(2);
        assertThat(Objects.requireNonNull(response.getBody())[0].getId()).isEqualTo(1);
        assertThat(Objects.requireNonNull(response.getBody())[1].getId()).isEqualTo(2);
        assertThat(Objects.requireNonNull(response.getBody())[0].getRoomType().getName()).isEqualTo("Double Room");
        assertThat(Objects.requireNonNull(response.getBody())[1].getRoomType().getName()).isEqualTo("Single Room");
    }

    @Test
    void testRoomSearchPage() throws Exception {
        String url = "http://localhost:" + port + "/rooms/search?peopleCount=2&start=2025-06-01&end=2025-06-05";
        String response = this.restTemplate.getForObject(url, String.class);
        assertThat(response).contains("rooms/search");
    }
}