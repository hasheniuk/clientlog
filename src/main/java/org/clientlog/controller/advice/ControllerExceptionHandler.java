package org.clientlog.controller.advice;

import org.clientlog.exception.InvalidPageRequestException;
import org.clientlog.exception.UniqueConstraintViolationException;
import org.hsqldb.HsqlException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            EmptyResultDataAccessException.class,
            EntityNotFoundException.class,
    })
    public void handleNotFound() {}

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
            InvalidPageRequestException.class,
    })
    public void handleBadRequest() {}

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UniqueConstraintViolationException.class)
    public void handleConflict() {}
}
