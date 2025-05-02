package com.bd.cybersentinel.service.exception;

import com.bd.cybersentinel.service.impl.CommonFunctionsImpl;
import com.bd.cybersentinel.util.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler extends CommonFunctionsImpl {

    @ExceptionHandler(NullPointerException.class)
    public Response<?> handleNullPointerException(NullPointerException ex) {
        return getErrorResponse("A null pointer exception occurred. Please contact support. \n\n Cause: \n" + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response<?> handleGeneralException(Exception ex) {
        return getErrorResponse("An unexpected error occurred. Please contact support. \n\n Cause: \n" + ex.getMessage());
    }
}
