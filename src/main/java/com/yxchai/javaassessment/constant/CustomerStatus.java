package com.yxchai.javaassessment.constant;

import lombok.Data;

public enum CustomerStatus {
    CUSTOMER_CREATED ("Customer Created"),
    CUSTOMER_UPDATED ("Customer Updated"),
    CUSTOMER_CREATE_FAILED ("Customer Create Failed"),
    CUSTOMER_UPDATE_FAILED ("Customer Update Failed");

    private final String text;

    private CustomerStatus (String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
