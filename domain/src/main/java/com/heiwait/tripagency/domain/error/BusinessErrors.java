package com.heiwait.tripagency.domain.error;

public enum BusinessErrors {

    MISSING_DESTINATION("error.destination.missing");

    private final String code;

    BusinessErrors(final String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}