package com.heiwait.tripagency.infrastructure.driver.exposition.rest.error;

import com.heiwait.tripagency.domain.error.BusinessErrors;
import com.heiwait.tripagency.domain.error.BusinessException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.inject.Inject;

@ControllerAdvice
public class ControllerExceptionTranslator {

    @Inject
    private MessageSource messageSource;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessage>  translateBusinessException(BusinessException bex) {
        BusinessErrors error = bex.getError();
        String description = messageSource.getMessage(error.getCode(), bex.getParams(), error.getCode(), LocaleContextHolder.getLocale());
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), description), error.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> translateException(Exception ex) {
        String code = TechnicalErrorConstants.ERR_INTERNAL_SERVER;
        String description = ex.getMessage();
        return new ResponseEntity<>(new ErrorMessage(code, description), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
