package com.hotel.hotelManagement.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;


    private String firstName;


    private String lastName;


    private String position;


    private String phone;


    private String address;
}
