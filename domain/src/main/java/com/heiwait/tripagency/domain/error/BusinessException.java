package com.heiwait.tripagency.domain.error;

public class BusinessException extends RuntimeException {
    private final BusinessErrors error;
    private final String[] params;

    public BusinessException(final BusinessErrors error, final String... params) {
        super();
        this.error = error;
        this.params = params;
    }

    public BusinessErrors error() {
        return error;
    }

    public String[] params() {
        return params;
    }
}