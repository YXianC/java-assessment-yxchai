package com.yxchai.javaassessment.service;

import com.yxchai.javaassessment.dto.CustomerDto;
import com.yxchai.javaassessment.exception.CustomerServiceCustomException;
import com.yxchai.javaassessment.exception.CustomerServiceException;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto) throws CustomerServiceException;
    CustomerDto updateCustomer(Long id, CustomerDto customerDto) throws CustomerServiceException, CustomerServiceCustomException;
    Page<CustomerDto> listCustomers() throws CustomerServiceException, CustomerServiceCustomException;
    String callThirdPartyAPI() throws CustomerServiceException;

}
