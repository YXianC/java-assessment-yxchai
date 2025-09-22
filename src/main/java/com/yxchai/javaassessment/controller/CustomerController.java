package com.yxchai.javaassessment.controller;

import com.yxchai.javaassessment.dto.CustomerDto;
import com.yxchai.javaassessment.exception.CustomerServiceCustomException;
import com.yxchai.javaassessment.exception.CustomerServiceException;
import com.yxchai.javaassessment.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;

    @Autowired
    public CustomerController (CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@Validated({Default.class, CustomerDto.AddGroup.class}) @RequestBody CustomerDto customerDto) throws CustomerServiceException {
        log.info("CustomerController.addCustomer()");
        CustomerDto responseCustomerDto = customerService.createCustomer(customerDto);
        log.debug("CustomerController.addCustomer() response : {}", responseCustomerDto);
        return ResponseEntity.ok(responseCustomerDto);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<CustomerDto>> listCustomer() throws CustomerServiceException, CustomerServiceCustomException {
        log.info("CustomerController.listCustomer()");
        Page<CustomerDto> customerDtoPage = customerService.listCustomers();
        return ResponseEntity.ok(customerDtoPage);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDto customerDto) throws CustomerServiceException, CustomerServiceCustomException{
        log.info("CustomerController.updateCustomer()");
        CustomerDto responseCustomerDto = customerService.updateCustomer(id, customerDto);
        log.debug("CustomerController.updateCustomer() reponse : {}", responseCustomerDto);
        return ResponseEntity.ok(responseCustomerDto);
    }
}
