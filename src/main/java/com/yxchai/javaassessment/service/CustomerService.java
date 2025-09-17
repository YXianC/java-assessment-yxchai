package com.yxchai.javaassessment.service;

import com.yxchai.javaassessment.dto.CustomerDto;
import org.springframework.data.domain.Page;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    Page<CustomerDto> listCustomers();
    String callThirdPartyAPI();

}
