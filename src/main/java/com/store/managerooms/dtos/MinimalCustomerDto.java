package com.store.managerooms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinimalCustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
}