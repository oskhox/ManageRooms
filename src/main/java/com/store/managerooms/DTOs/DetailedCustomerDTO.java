package com.store.managerooms.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailedCustomerDTO {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}