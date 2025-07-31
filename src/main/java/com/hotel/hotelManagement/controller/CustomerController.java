package com.hotel.hotelManagement.controller;

import com.hotel.hotelManagement.serviceImpl.CustomerServiceImpl;
import com.hotel.hotelManagement.dto.CustomerDto;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
private CustomerServiceImpl customerService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllCustomers() {
        APIResponse response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-or-update")
public ResponseEntity<APIResponse> creteOrUpdate(@RequestBody CustomerDto customerDto){
        APIResponse response=customerService.creteOrUpdateUser(customerDto);
        return ResponseEntity.ok(response);
    }
@GetMapping("/{id}")
    public ResponseEntity<APIResponse> getCustomerById(@PathVariable Long id){
        APIResponse response =customerService.getCustomerById(id);
        return  ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
public ResponseEntity<APIResponse> deletedById(@PathVariable Long id){
        APIResponse response =customerService.deleteCustomer(id);
        return ResponseEntity.ok(response);
}

}
