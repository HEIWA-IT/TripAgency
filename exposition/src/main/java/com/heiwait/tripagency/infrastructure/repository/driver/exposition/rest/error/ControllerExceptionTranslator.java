package com.heiwait.tripagency.infrastructure.repository.driver.exposition.rest.error;

import com.heiwait.tripagency.domain.error.BusinessErrors;
import com.heiwait.tripagency.domain.error.BusinessException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionTranslator {

    private MessageSource messageSource;

    ControllerExceptionTranslator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorMessage> translateBusinessException(BusinessException bex) {
        BusinessErrors error=bex.getError();
        String description = messageSource.getMessage(error.getCode(), bex.getParams(), error.getCode(), LocaleContextHolder.getLocale());
        HttpStatus httpStatus = HttpStatus.resolve(error.getHttpCode());
        assert httpStatus != null;
        return new ResponseEntity<>(new ErrorMessage(error.getCode(), description), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> translateException(Exception ex) {
        String code=TechnicalErrorConstants.ERR_INTERNAL_SERVER;
        String description = ex.getMessage();
        return new ResponseEntity<>(new ErrorMessage(code, description), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
