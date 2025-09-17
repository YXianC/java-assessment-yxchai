package com.yxchai.javaassessment.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 4127057410099210130L;

    private Long customerId;
    private String name;
    private String email;
    private String status;
}
