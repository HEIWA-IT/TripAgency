package com.heiwait.tripagency.pricer.specifications.cucumber;

import java.util.ResourceBundle;

public class ErrorMessagesProperties {
    private static final String FILENAME = "messages";
    private final static ResourceBundle rb = ResourceBundle.getBundle(FILENAME);

    private ErrorMessagesProperties() {
    }

    public static String getErrorMessageFromErrorCode(String errorCode) {
        return rb.getString(errorCode);
    }
}