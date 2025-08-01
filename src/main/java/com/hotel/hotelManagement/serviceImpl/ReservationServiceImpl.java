package com.hotel.hotelManagement.serviceImpl;

import com.hotel.hotelManagement.dto.ReservationDto;
import com.hotel.hotelManagement.entity.Customer;
import com.hotel.hotelManagement.entity.Reservation;
import com.hotel.hotelManagement.entity.Room;
import com.hotel.hotelManagement.enums.RoomStatus;
import com.hotel.hotelManagement.exception.BadRequestException;
import com.hotel.hotelManagement.exception.ResourceNotFoundException;
import com.hotel.hotelManagement.exception.UserNotFoundException;
import com.hotel.hotelManagement.iService.IReservationService;
import com.hotel.hotelManagement.repository.CustomerRepo;
import com.hotel.hotelManagement.repository.ReservationRepo;
import com.hotel.hotelManagement.repository.RoomRepo;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {
    @Autowired
    ReservationRepo reservationRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    RoomRepo roomRepo;
    @Override
    public APIResponse getAllReservations() {
        List<Reservation> allReservations=reservationRepo.findAll();
        APIResponse response=new APIResponse();
        response.setMessage("Reservations fetched successfully");
        response.setData(allReservations);
        return response;
    }

    @Override
    public APIResponse getReservationById(Long id) {
        Reservation reservation=reservationRepo.findById(id).orElse(null);
        if(reservation==null){
            throw  new UserNotFoundException("Reservation with"+id+" not found in system");
        }
        APIResponse response=new APIResponse();
        response.setMessage("Reservation fetched successfully");
        response.setData(reservation);
        return response;
    }

    @Override
    public APIResponse createReservation(ReservationDto reservationDto) {
        if (reservationDto.getId() != null) {
            throw new IllegalArgumentException("Id should be null when creating a new reservation");
        }

        // Check if the customer exists
        Customer customer = customerRepo.findById(reservationDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer with "+reservationDto.getCustomerId()+" not found"));

        // Check if the room exists
        Room room = roomRepo.findById(reservationDto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room with "+reservationDto.getRoomId()+" not found"));

        // Check if the room is already reserved in the given date range according to the expected arrival and leaving time
        if (reservationRepo.existsByRoomIdAndDateRangeOverlap(reservationDto.getRoomId(), reservationDto.getExpectedArrivalTime(), reservationDto.getExpectedLeavingTime())) {
            throw new BadRequestException("Room is already reserved in the given date range");
        }
     Reservation reservation=new Reservation();
        BeanUtils.copyProperties(reservationDto,reservation);
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        Reservation savedReservation=reservationRepo.save(reservation);
        room.setRoomStatus(RoomStatus.RESERVED);
        roomRepo.save(room);
        APIResponse response=new APIResponse();
        response.setData(savedReservation);
        response.setMessage("Reservation created successfully");

        return response;
    }

    @Override
    @Transactional
    public APIResponse updateReservation(Long id, ReservationDto reservationDto) {
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with " + id + " not found"));

        Customer customer = customerRepo.findById(reservationDto.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer with " + reservationDto.getCustomerId() + " not found"));

        Room room = roomRepo.findById(reservationDto.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("Room with " + reservationDto.getRoomId() + " not found"));

        // Check if new room is already reserved (only if room changed)
        if (!reservation.getRoom().getId().equals(reservationDto.getRoomId()) && room.getRoomStatus().equals(RoomStatus.RESERVED)) {
            throw new BadRequestException("Room is already reserved");
        }

        // Check for overlapping reservations
        if (reservationRepo.existsByRoomIdAndExpectedArrivalTimeLessThanEqualAndExpectedLeavingTimeGreaterThanEqualAndIdNot(
                reservationDto.getRoomId(), reservationDto.getExpectedLeavingTime(), reservationDto.getExpectedArrivalTime(), id)) {
            throw new BadRequestException("Room is already reserved in the given date range");
        }

        // Update reservation fields
        reservation.setExpectedArrivalTime(reservationDto.getExpectedArrivalTime());
        reservation.setExpectedLeavingTime(reservationDto.getExpectedLeavingTime());
        reservation.setCustomer(customer);
        reservation.setRoom(room);

        reservation = reservationRepo.save(reservation);

        // Update room status dynamically
        if (!room.getRoomStatus().equals(RoomStatus.RESERVED)) {
            room.setRoomStatus(RoomStatus.RESERVED);
            roomRepo.save(room);
        }

        return new APIResponse("ok",200,"Reservation updated successfully", reservation);
    }

    @Override
    public APIResponse deleteReservation(Long id) {
        Reservation reservation = reservationRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Reservation with id not found")
        );
        reservationRepo.delete(reservation);
        return new APIResponse("ok",200,"Reservation deleted successfully",null);
    }

    @Override
    public APIResponse checkIn(Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId).orElseThrow(() ->
                new ResourceNotFoundException("Reservation not found")
        );

        Room room = reservation.getRoom();
        room.setRoomStatus(RoomStatus.RESERVED);
        roomRepo.save(room);

        reservation.setCheckInDate(new Date());
        reservationRepo.save(reservation);

        return new APIResponse("ok",200,"Checked in successfully",null);
    }

    @Override
    public APIResponse checkOut(Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId).orElseThrow(() ->
                new ResourceNotFoundException("Reservation not found")
        );
        Room room = reservation.getRoom();
        room.setRoomStatus(RoomStatus.AVAILABLE);
        roomRepo.save(room);

        reservation.setCheckOutDate(new Date());
        reservationRepo.save(reservation);
        return new APIResponse("ok",200,"Check out successfully",null);
    }

    @Override
    public APIResponse requestCancellation(Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId).orElseThrow(() ->
                new ResourceNotFoundException("Reservation not found")
        );

        return null;
    }
}
