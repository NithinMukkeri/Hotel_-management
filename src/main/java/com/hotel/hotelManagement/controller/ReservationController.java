package com.hotel.hotelManagement.controller;

import com.hotel.hotelManagement.dto.ReservationDto;
import com.hotel.hotelManagement.entity.Reservation;
import com.hotel.hotelManagement.iService.IReservationService;
import com.hotel.hotelManagement.response.APIResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
IReservationService reservationService;
    @GetMapping("/all")
public ResponseEntity<APIResponse> getAllReservations(){
    APIResponse response =reservationService.getAllReservations();
    return   ResponseEntity.ok(response);
}

@PostMapping("/")
public ResponseEntity<APIResponse> createReservation(@Valid @RequestBody ReservationDto reservationDto){
        APIResponse response=reservationService.createReservation(reservationDto);
        return ResponseEntity.ok(response);
}

@GetMapping("/{id}")
public  ResponseEntity<APIResponse> getReservationById(Long id){
        APIResponse response=reservationService.getReservationById(id);
        return ResponseEntity.ok(response);
}
@DeleteMapping("/delete/{id}")
public  ResponseEntity<APIResponse> deleteById(Long id){
        APIResponse response=reservationService.deleteReservation(id);
        return ResponseEntity.ok(response);
}

@PostMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto){
        APIResponse response=reservationService.updateReservation(id,reservationDto);
        return ResponseEntity.ok(response);
}

    @PostMapping("/checkin/{id}")
    public ResponseEntity<APIResponse> checkIn(@PathVariable Long id) {
        APIResponse response = reservationService.checkIn(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<APIResponse> checkOut(@PathVariable Long id) {
        APIResponse response = reservationService.checkOut(id);
        return ResponseEntity.ok(response);
    }



}
