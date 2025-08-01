package com.hotel.hotelManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "floor_number", nullable = false)
    private Integer floorNumber;

    @Column(name = "bed_number", nullable = false)
    private Integer bedNumber;

    @Column(name = "room_info", nullable = false)
    private String roomInfo;

    @Column(name = "room_status", nullable = false)
    private String roomStatus;

    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;
}
