package com.heiwait.tripagency.domain.error;

import java.net.HttpURLConnection;

public enum BusinessErrors {

    MISSING_DESTINATION("error.destination.missing", HttpURLConnection.HTTP_NOT_FOUND);

    private final String code;
    private final int httpCode;

    BusinessErrors(final String code, int httpCode) {
        this.code = code;
        this.httpCode = httpCode;
    }

    public String getCode() {
        return code;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
