package com.yxchai.javaassessment.constant;

public enum CustomerStatus {
    CUSTOMER_CREATED ("Customer Created"),
    CUSTOMER_UPDATED ("Customer Updated"),
    FAILED ("FAILED");

    private final String text;

    private CustomerStatus (String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
