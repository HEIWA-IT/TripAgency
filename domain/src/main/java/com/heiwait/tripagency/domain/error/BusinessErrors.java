package com.heiwait.tripagency.domain.error;

import java.net.HttpURLConnection;

public enum BusinessErrors {

    MISSING_DESTINATION("error.destination.missing", HttpURLConnection.HTTP_BAD_REQUEST);

    private final String code;
    private final int httpStatus;

    BusinessErrors(final String code, int httpStatus) {
        this.code = code;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
