package com.store.managerooms.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedCustomerDto {
    private Long id;

    @NotEmpty(message = "Förnamn krävs")
    private String firstName;

    @NotEmpty(message = "Efternamn krävs")
    private String lastName;

    @NotEmpty(message = "E-post krävs")
    private String email;

    @NotEmpty(message = "Telefonnummer krävs")
    private String phone;
}