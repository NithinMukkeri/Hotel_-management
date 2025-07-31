package com.hotel.hotelManagement.serviceImpl;

import com.hotel.hotelManagement.iService.ICustomerService;
import com.hotel.hotelManagement.dto.CustomerDto;
import com.hotel.hotelManagement.entity.Customer;
import com.hotel.hotelManagement.exception.UserNotFoundException;
import com.hotel.hotelManagement.repository.CustomerRepo;
import com.hotel.hotelManagement.response.APIResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {
    @Autowired
    private final CustomerRepo customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepository) {
        this.customerRepository = customerRepository;
    }

    public APIResponse getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();


APIResponse aapiresponse=new APIResponse();
        aapiresponse.setStatus("ok");
        aapiresponse.setData(customers);
        aapiresponse.setHttpStatus(HttpStatus.OK.value());
        aapiresponse.setMessage("Total users found: " + customers.size());
        return aapiresponse;
    }

    @Override
    public APIResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer== null){
            throw new UserNotFoundException("No users found in the system.");
        }
CustomerDto dto=new CustomerDto();
        BeanUtils.copyProperties(customer,dto);

//        APIResponse response=new APIResponse();
//        response.setData(dto);
//        response.setMessage("Customer fetched successfully");
//        response.setHttpStatus(HttpStatus.OK.value());
////return ResponseEntity.ok(response);
        return new APIResponse("ok",HttpStatus.OK.value(),"Customer fetched successfully",dto);



    }

    @Override
    public APIResponse creteOrUpdateUser(CustomerDto customerDto) {
        APIResponse response=new APIResponse();
try{
   Customer customer;
   boolean isUpdate=false;
   if(customerDto.getId()!=0 & customerRepository.existsById(customerDto.getId())){
customer=customerRepository.getReferenceById(customerDto.getId());
    BeanUtils.copyProperties(customerDto,customer);
    isUpdate=true;
       }
       else{
        customer=new Customer();
        BeanUtils.copyProperties(customerDto,customer);
       }
Customer savedCustomer=customerRepository.save(customer);

response.setHttpStatus(HttpStatus.OK.value());
response.setStatus("ok");
response.setStatus(isUpdate?"User updated successfully" : "User created successfully");
response.setData(savedCustomer);
return  response;
}catch (Exception e){
response.setMessage("User creation/update failed: " + e.getMessage());
response.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
return response;
        }

    }

    @Override
    public APIResponse deleteCustomer(Long id) {
        APIResponse response= new APIResponse();
        Optional<Customer> customer= customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }

        customerRepository.deleteById(id);
        response.setMessage("User deleted successfully");
        response.setHttpStatus(HttpStatus.OK.value());
        response.setData(null);
        return response;
    }


}
