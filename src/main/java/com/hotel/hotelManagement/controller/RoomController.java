package com.hotel.hotelManagement.controller;


import com.hotel.hotelManagement.dto.RoomDto;
import com.hotel.hotelManagement.iService.IRoomService;

import com.hotel.hotelManagement.response.APIResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("room")
@RestController
public class RoomController {
    @Autowired
    IRoomService roomService;

    @GetMapping("/all")
public ResponseEntity<APIResponse> getAllRooms(){
    APIResponse response=roomService.getAllRooms();
    return ResponseEntity.ok(response);
}
@PostMapping("/create-room")
public ResponseEntity<APIResponse> createRoom(@Valid @RequestBody RoomDto roomDto){
        APIResponse response=roomService.createRoom(roomDto);
        return ResponseEntity.ok(response);
}
@PutMapping("/update/{id}")
public  ResponseEntity<APIResponse> updateRooom(@Valid @RequestBody RoomDto roomDto,@PathVariable Long id){
        APIResponse response= roomService.updateRoom(id,roomDto);
        return ResponseEntity.ok(response);
}
@GetMapping("/{id}")
public ResponseEntity<APIResponse> getRoomById(@PathVariable Long id){
        APIResponse response=roomService.getRoomById(id);
        return ResponseEntity.ok(response);
}

public ResponseEntity<APIResponse> deleteById(@PathVariable Long id){
        APIResponse response=roomService.deleteRoom(id);
        return ResponseEntity.ok(response);
}
}
