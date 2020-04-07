package com.disney.studios.Exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 *  Global Exception handler
 *  Created by: Indrajit Singh
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     *
     * validation exception
     * @return @ResponseStatus(HttpStatus.BAD_REQUEST)
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage(e.getMessage());
    }

    /**
     * constraint violation exception
     *
     * @return @ResponseStatus(HttpStatus.BAD_REQUEST)
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorMessage exceptionHandler(ConstraintViolationException e) {
        return new ErrorMessage(e.getMessage());
    }

    /**
     * Entity not found exception
     *
     * @return  @ResponseStatus(HttpStatus.BAD_REQUEST)
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorMessage exceptionHandler(EntityNotFoundException e){
        return new ErrorMessage((e.getMessage()));
    }

    /**
     *
     * Generic Exception
     * @return @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorMessage exceptionHandler(Exception e){
        return new ErrorMessage((e.getMessage()));
    }
}
