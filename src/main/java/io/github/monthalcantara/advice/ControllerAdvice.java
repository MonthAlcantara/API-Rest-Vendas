package io.github.monthalcantara.advice;

import io.github.monthalcantara.exception.BusinessRuleException;
import io.github.monthalcantara.exception.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(BusinessRuleException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError error(BusinessRuleException e){
        return new ApiError(e.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError orderNotFound(OrderNotFoundException e){
        return new ApiError(e.getMessage());
    }

}
