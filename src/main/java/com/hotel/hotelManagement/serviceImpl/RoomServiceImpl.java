package com.hotel.hotelManagement.serviceImpl;

import com.hotel.hotelManagement.dto.RoomDto;
import com.hotel.hotelManagement.entity.Room;
import com.hotel.hotelManagement.exception.BadRequestException;
import com.hotel.hotelManagement.exception.UserNotFoundException;
import com.hotel.hotelManagement.iService.IRoomService;
import com.hotel.hotelManagement.repository.RoomRepo;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RoomServiceImpl implements IRoomService {
    @Autowired
    RoomRepo roomRepository;
    @Override
    public APIResponse getAllRooms() {
     APIResponse response=new APIResponse();
     List<Room> allRooms=roomRepository.findAll();
     response.setMessage("Rooms fetched successfully");
     response.setData(allRooms);
        return response;
    }

    @Override
    public APIResponse getRoomById(Long id) {
        Room room=roomRepository.findById(id).orElse(null);
        if(room==null){
            throw new UserNotFoundException("room with"+id+" not found");
        }
        APIResponse response=new APIResponse();
        response.setData(room);
        response.setMessage("Room fetched successfully");
        return response;
    }

    @Override
    public APIResponse createRoom(RoomDto roomDto) {
        if (roomDto.getId() != null) {
            throw new BadRequestException("Id should be null when creating a new room");
        }
        if (roomRepository.findByRoomNumber(roomDto.getRoomNumber()) != null) {
            throw new BadRequestException("Room with room number " + roomDto.getRoomNumber() + " already exists");
        }
        Room room=new Room();
        BeanUtils.copyProperties(roomDto,room);
        Room room1=roomRepository.save(room);
        APIResponse response=new APIResponse();
        response.setMessage("Room created successfully");
        response.setData(room1);
        return response;
    }

    @Override
    public APIResponse updateRoom(Long id, RoomDto roomDto) {
        try{

                Room room=roomRepository.findById(id).orElse(null);
            if(room==null){
                throw new UserNotFoundException("Room with "+id+" not found");
            }
            BeanUtils.copyProperties(roomDto,room);
            Room room1=roomRepository.save(room);
            APIResponse response=new APIResponse();
            response.setData(room1);
            response.setMessage("Room updated  successfully");

        }catch (Exception e){
           throw  new BadRequestException("bad request") ;
        }
        return null;
    }

    @Override
    public APIResponse deleteRoom(Long id) {
        Room room=roomRepository.findById(id).orElse(null);
        if(room==null){
throw  new UserNotFoundException("Room with "+ id+" not found");
        }
roomRepository.deleteById(id);
        APIResponse response=new APIResponse();
        response.setMessage("Room deleted successfully");
        return response;
    }
}
