package com.hotel.hotelManagement.iService;

import com.hotel.hotelManagement.dto.CustomerDto;

import com.hotel.hotelManagement.response.APIResponse;

import org.springframework.stereotype.Service;

@Service
public interface ICustomerService {
    APIResponse getAllCustomers() ;

  APIResponse  getCustomerById(Long id);

 APIResponse creteOrUpdateUser(CustomerDto customerDto);

APIResponse  deleteCustomer(Long id);

}
