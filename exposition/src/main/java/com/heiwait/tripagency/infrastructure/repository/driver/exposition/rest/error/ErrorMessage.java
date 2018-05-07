package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.error;

public class ErrorMessage {
    private String code;
    private String description;

    ErrorMessage(String code) {
        this(code, null);
    }

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
