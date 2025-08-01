package com.hotel.hotelManagement.serviceImpl;

import com.hotel.hotelManagement.dto.EmployeeDto;
import com.hotel.hotelManagement.entity.Employee;
import com.hotel.hotelManagement.exception.UserNotFoundException;
import com.hotel.hotelManagement.iService.IEmployeeService;
import com.hotel.hotelManagement.repository.EmployeeRepo;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeRepo employeeRepopository;

    @Override
    public APIResponse getAllEmployees() {
        List<Employee> allEmployees=employeeRepopository.findAll();

        APIResponse response= new APIResponse();
        response.setData(allEmployees);
        response.setMessage("Employees fetched successfully");
        response.setHttpStatus(HttpStatus.OK.value());
        return response;
    }

    @Override
    public APIResponse getEmployeeById(Long id) {
       Employee employee= employeeRepopository.findById(id).orElse( null);
        if(employee==null){
            throw  new UserNotFoundException("No employee found in the system.");
        }

        return new APIResponse("ok",HttpStatus.OK.value(),"Employee fetched successfully",employee);
    }

    @Override
    public APIResponse creteOrUpdateUser(EmployeeDto employeeDto) {
        APIResponse response=new APIResponse();

        try{
            boolean isUpdate=false;
            Employee employee ;
            if( employeeDto.getId()!=null && employeeDto.getId() !=0   && employeeRepopository.existsById(employeeDto.getId())){
               employee=employeeRepopository.getReferenceById(employeeDto.getId());
                BeanUtils.copyProperties(employeeDto,employee);
                isUpdate=true;
            }else{
                employee=new Employee();
                BeanUtils.copyProperties(employeeDto,employee);
            }
          Employee savedEmployee=  employeeRepopository.save(employee);
            response.setStatus(HttpStatus.OK.toString());
            response.setData(savedEmployee);
            response.setMessage(isUpdate? "Employee updated successfully": "Employee created successfully");
            return response;

        } catch (Exception e) {
            response.setMessage("Employee creation/update failed: " + e.getMessage());
            response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            return response;
        }

    }



    @Override
    public APIResponse deleteEmployee(Long id) {
        APIResponse response= new APIResponse();
        Optional<Employee> employee= employeeRepopository.findById(id);
        if(employee.isEmpty()){
            throw new UserNotFoundException("Emplopyee with ID " + id + " not found.");
        }
        //customerRepository.deleteById(id);
        employeeRepopository.deleteById(id);
        response.setMessage("User deleted successfully");
        response.setHttpStatus(HttpStatus.OK.value());
        response.setData(null);
        return response;

    }
}
