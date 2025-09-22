package com.yxchai.javaassessment.service.impl;

import com.yxchai.javaassessment.constant.CustomerStatus;
import com.yxchai.javaassessment.dto.CustomerDto;
import com.yxchai.javaassessment.entity.Customer;
import com.yxchai.javaassessment.exception.CustomerServiceCustomException;
import com.yxchai.javaassessment.exception.CustomerServiceException;
import com.yxchai.javaassessment.repository.CustomerRepository;
import com.yxchai.javaassessment.service.CustomerService;
import com.yxchai.javaassessment.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private CustomerRepository customerRepository;
    private RestTemplate restTemplate;
    private int pageSize = 10;
    private String thirdPartyApiUrl = "https://jsonplaceholder.typicode.com/posts/1";

    @Autowired
    public CustomerServiceImpl (CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto customerDto) throws CustomerServiceException {
        log.info("CustomerServiceImpl.createCustomer()");
        try {
            Customer customer = ObjectMapperUtils.map(customerDto, Customer.class);
            customerRepository.save(customer);
            log.info("Customer saved with ID : {}", customer.getCustomerId());
            customerDto = ObjectMapperUtils.map(customer, CustomerDto.class);
            customerDto.setStatus(CustomerStatus.CUSTOMER_CREATED.getText());
            return customerDto;
        } catch (Exception e) {
            log.error("Failed to create new customer : {}", e.getMessage());
            throw new CustomerServiceException();
        }
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) throws CustomerServiceException, CustomerServiceCustomException {
        log.info("CustomerServiceImpl.updateCustomer()");
        try{
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new CustomerServiceCustomException("Customer not found with ID " + id));

            if(customerDto.getName() != null) {
                customer.setName(customerDto.getName());
            }

            if(customerDto.getEmail() != null) {
                customer.setEmail(customerDto.getEmail());
            }

            customerRepository.save(customer);
            log.info("Customer with ID {} updated", customer.getCustomerId());
            customerDto = ObjectMapperUtils.map(customer, CustomerDto.class);
            customerDto.setStatus(CustomerStatus.CUSTOMER_UPDATED.getText());
            return customerDto;
        } catch (CustomerServiceCustomException e) {
            log.error("Failed to update customer : {}", e.getMessage());
            throw new CustomerServiceCustomException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Failed to update customer : {}", e.getMessage());
            throw new CustomerServiceException();
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDto> listCustomers() throws CustomerServiceException, CustomerServiceCustomException{
        log.info("CustomerServiceImpl.listCustomers()");
        try{
            Page<Customer> customerList = customerRepository.findAll(PageRequest.of(0, pageSize));

            if(customerList.isEmpty()) {
                log.info("No Customer Found");
                throw new CustomerServiceCustomException("No Customer Found");
            }

            return customerList.map(customer -> ObjectMapperUtils.map(customer, CustomerDto.class));
        } catch (CustomerServiceCustomException e) {
            throw new CustomerServiceCustomException(e.getMessage());
        }
        catch (Exception e) {
            log.error("Failed to fetch all customers : {}", e.getMessage());
            throw new CustomerServiceException();
        }
    }

    @Override
    public String callThirdPartyAPI() throws CustomerServiceException {
        log.info("CustomerServiceImpl.callThirdPartyAPI()");
        try{
            return restTemplate.getForObject(thirdPartyApiUrl, String.class);
        } catch (Exception e) {
            throw new CustomerServiceException();
        }

    }
}
