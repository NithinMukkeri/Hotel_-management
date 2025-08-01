package com.hotel.hotelManagement.iService;

import com.hotel.hotelManagement.dto.EmployeeDto;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.stereotype.Service;

@Service
public interface IEmployeeService {
    APIResponse getAllEmployees();
    APIResponse getEmployeeById(Long id);

    APIResponse creteOrUpdateUser(EmployeeDto employeeDto);


    APIResponse deleteEmployee(Long id);
}
