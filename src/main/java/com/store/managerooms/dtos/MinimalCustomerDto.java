package com.store.managerooms.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinimalCustomerDto {

    @NotNull(message = "Du måste välja en kund")
    private Long id;

    private String firstName;
    private String lastName;

}