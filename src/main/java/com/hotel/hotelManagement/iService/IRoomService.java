package com.hotel.hotelManagement.iService;

import com.hotel.hotelManagement.dto.RoomDto;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoomService {
    APIResponse getAllRooms();

    APIResponse getRoomById(Long id);

    APIResponse createRoom(RoomDto roomDto);

    APIResponse updateRoom(Long id, RoomDto roomDto);

    APIResponse deleteRoom(Long id);

}
