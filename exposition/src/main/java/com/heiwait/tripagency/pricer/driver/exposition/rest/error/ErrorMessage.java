package com.heiwait.tripagency.pricer.driver.exposition.rest.error;

public class ErrorMessage {
    private final String code;
    private final String description;

    ErrorMessage(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
