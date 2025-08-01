package com.hotel.hotelManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.sound.midi.Sequence;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(length = 50)
    private String position;

    @Column(length = 15)
    private String phone;

    @Column(length = 80)
    private String address;
}
