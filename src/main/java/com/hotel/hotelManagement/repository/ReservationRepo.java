package com.hotel.hotelManagement.repository;

import com.hotel.hotelManagement.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation,Long> {
    boolean existsByRoomIdAndExpectedArrivalTimeLessThanEqualAndExpectedLeavingTimeGreaterThanEqualAndIdNot(Long roomId, Date expectedLeavingTime, Date expectedArrivalTime, Long id);

    @Query("SELECT COUNT(r) > 0 FROM Reservation r WHERE r.room.id = :roomId AND r.expectedArrivalTime < :endDate AND r.expectedLeavingTime > :startDate")
    boolean existsByRoomIdAndDateRangeOverlap(@Param("roomId") Long roomId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
