package com.hotel.hotelManagement.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationDto {
    private Long id;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Room ID is required")
    private Long roomId;

    private Date checkInDate;

    private Date checkOutDate;

    //expected arrival time
    @NotNull(message = "Expected arrival time is required")
    private Date expectedArrivalTime;

    //expected leaving time
    @NotNull(message = "Expected leaving time is required")
    private Date expectedLeavingTime;

    private LocalDateTime createdAt = LocalDateTime.now();
}
