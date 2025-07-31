package com.hotel.hotelManagement.repository;

import com.hotel.hotelManagement.entity.Customer;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepo extends JpaRepository<Customer,Long> {
    Customer findByPhone(String phone);
    List<Customer> findByFirstNameContaining(String Firstname);
    List<Customer> findByLastNameContaining(String Lastname);
    Customer findByFirstName(String john);
    Customer findById(long id);
}
