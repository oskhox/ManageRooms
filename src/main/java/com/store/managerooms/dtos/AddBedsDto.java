package com.store.managerooms.dtos;



import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBedsDto {

    @NotNull
    private Long roomTypeId;

    @NotNull
    private int beds;
}
