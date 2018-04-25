package com.heiwait.tripagency.domain.error;

import org.springframework.http.HttpStatus;

public enum BusinessErrors {

    MISSING_DESTINATION("error.destination.missing", HttpStatus.BAD_REQUEST);

    private final String code;
    private final HttpStatus httpStatus;

    BusinessErrors(final String code, HttpStatus httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
