package com.heiwait.tripagency.pricer.domain.error;

public class BusinessException extends RuntimeException {
    private final BusinessErrors error;

    public BusinessException(final BusinessErrors error) {
        super();
        this.error = error;
    }

    public BusinessErrors error() {
        return error;
    }
}