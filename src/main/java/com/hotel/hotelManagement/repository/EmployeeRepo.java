package com.hotel.hotelManagement.repository;

import com.hotel.hotelManagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Employee findByPhone(String phone);
}
