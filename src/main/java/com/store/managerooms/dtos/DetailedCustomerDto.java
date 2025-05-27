package com.store.managerooms.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Ogiltig e-postadress"
    )
    private String email;

    @NotEmpty(message = "Telefonnummer krävs")
    @Pattern(regexp = "^\\+\\d+$", message = "Telefonnummer måste börja med + och endast innehålla siffror")
    private String phone;
}