package com.hotel.hotelManagement.iService;

import com.hotel.hotelManagement.dto.ReservationDto;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IReservationService {
    APIResponse getAllReservations();

    APIResponse getReservationById(Long id);

    APIResponse createReservation(ReservationDto reservationDto);

    APIResponse updateReservation(Long id, ReservationDto reservationDto);

    APIResponse deleteReservation(Long id);

    APIResponse checkIn(Long reservationId);

    APIResponse checkOut(Long roomId);

    APIResponse requestCancellation(Long reservationId);
}
