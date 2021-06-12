package com.heiwait.tripagency.pricer.driver.exposition.rest.error;

import com.heiwait.tripagency.pricer.domain.error.BusinessErrors;
import com.heiwait.tripagency.pricer.domain.error.BusinessException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final MessageSource messageSource;

    private final PropertiesHttpCode propertiesHttpCode;

    ControllerExceptionHandler(MessageSource messageSource, PropertiesHttpCode propertiesHttpCode) {
        this.messageSource = messageSource;
        this.propertiesHttpCode = propertiesHttpCode;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessage> translateBusinessException(BusinessException bex) {
        BusinessErrors error = bex.error();
        String[] params = null;
        String description = messageSource.getMessage(error.code(), params, error.code(), LocaleContextHolder.getLocale());
        HttpStatus httpStatus = HttpStatus.resolve(propertiesHttpCode.getHttpCodeFromErrorCode(error.code()));
        assert httpStatus != null;
        return new ResponseEntity<>(new ErrorMessage(error.code(), description), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> translateException(Exception ex) {
        String code = TechnicalErrorConstants.ERR_INTERNAL_SERVER;
        String description = ex.getMessage();
        return new ResponseEntity<>(new ErrorMessage(code, description), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}