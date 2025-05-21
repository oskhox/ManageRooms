package com.store.managerooms.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedCustomerDTO {
    private Long id;

    @NotEmpty(message = "First name is mandatory")
    private String firstName;

    @NotEmpty(message = "Last name is mandatory")
    private String lastName;

    @NotEmpty(message = "Email is mandatory")
    private String email;

    @NotEmpty(message = "Phone number is mandatory")
    private String phone;
}