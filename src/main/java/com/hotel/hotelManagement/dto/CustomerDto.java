package com.hotel.hotelManagement.dto;

import lombok.*;
import lombok.Builder;
import lombok.Data;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String firstName;

    private String lastName;

    private String phone;

    private String address;
}
