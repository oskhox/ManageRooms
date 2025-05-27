package com.store.managerooms.BookingTest;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


public class BookingControllerMockMvcTest {


    private MockMvc mockMvc;

    @Test
    public void shouldReturnBookingUpdatePage() throws Exception {
        mockMvc.perform(get("/bookings/update-booking/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Uppdatera en bokning")));
    }
}
