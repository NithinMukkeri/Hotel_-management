package com.hotel.hotelManagement.controller;

import com.hotel.hotelManagement.dto.EmployeeDto;
import com.hotel.hotelManagement.iService.IEmployeeService;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
@GetMapping("/all")
    public ResponseEntity<APIResponse> getAllEmployees(){
        APIResponse response=employeeService.getAllEmployees();
        return  ResponseEntity.ok(response);
    }
    @PostMapping("/create-or-update")
    public ResponseEntity<APIResponse> createOrUpdateEmployee(@RequestBody EmployeeDto employeeDto){
    APIResponse response = employeeService.creteOrUpdateUser(employeeDto);
    return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<APIResponse> getEmployeeById(@PathVariable Long id){
    APIResponse response=employeeService.getEmployeeById(id);
    return  ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteEmployeeById(@PathVariable Long id){
    APIResponse response=employeeService.deleteEmployee(id);
    return ResponseEntity.ok(response);
    }
}
