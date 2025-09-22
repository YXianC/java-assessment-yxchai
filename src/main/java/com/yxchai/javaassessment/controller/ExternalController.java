package com.yxchai.javaassessment.controller;

import com.yxchai.javaassessment.exception.CustomerServiceException;
import com.yxchai.javaassessment.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external")
public class ExternalController {

    private static final Logger log = LoggerFactory.getLogger(ExternalController.class);
    private CustomerService customerService;

    @Autowired
    public ExternalController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/call")
    public ResponseEntity<String> callThirdPartyApi() throws CustomerServiceException {
        log.info("ExternalController.callThirdPartyApi()");
        String apiResponse = customerService.callThirdPartyAPI();
        log.debug("ExternalController.callThirdPartyApi() response : {}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }
}