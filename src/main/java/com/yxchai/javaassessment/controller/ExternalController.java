package com.yxchai.javaassessment.controller;

import com.yxchai.javaassessment.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/external")
public class ExternalController {

    private static final Logger log = LoggerFactory.getLogger(ExternalController.class);
    private RestTemplate restTemplate;
    private CustomerService customerService;

    @Autowired
    public ExternalController(RestTemplate restTemplate, CustomerService customerService) {
        this.restTemplate = restTemplate;
        this.customerService = customerService;
    }

    @GetMapping("/call")
    public ResponseEntity<String> callThirdPartyApi() {
        log.info("ExternalController.callThirdPartyApi()");
        String apiResponse = customerService.callThirdPartyAPI();
        log.debug("ExternalController.callThirdPartyApi() response : {}", apiResponse);
        return ResponseEntity.ok(apiResponse);
    }
}