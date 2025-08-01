package com.hotel.hotelManagement.dto;

import com.hotel.hotelManagement.enums.RoomStatus;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@Builder

@AllArgsConstructor
public class RoomDto {
    private Long id;

    @NotNull(message = "Floor number is required")
    private int floorNumber;

    @NotNull(message = "Bed number is required")
    private int bedNumber;

    @NotNull(message = "Room info is required")
    private String roomInfo;

    @NotNull(message = "Status is required")
    private RoomStatus roomStatus;

    @NotNull(message = "Room number is required")
    private int roomNumber;
}
