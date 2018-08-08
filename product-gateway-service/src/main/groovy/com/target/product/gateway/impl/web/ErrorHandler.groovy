package com.target.product.gateway.impl.web

import com.target.product.gateway.api.common.ErrorResponse
import com.target.product.gateway.impl.exception.EntityNotFoundException
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

import javax.servlet.ServletException
import javax.validation.ConstraintViolationException

@CompileStatic
@ControllerAdvice
@Slf4j
class ErrorHandler {
    @ExceptionHandler(Exception)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ErrorResponse handle(Exception e) {
        log.error('Exception: {}', e.message, e)
        return new ErrorResponse(message: e.message)
    }

    @ExceptionHandler(IllegalArgumentException)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ErrorResponse handle(IllegalArgumentException e) {
        String message = e.message
        return new ErrorResponse(message: message)
    }

    /** Requested entity could not be found
     * Returns a 404 for a missing root entity
     * Returns a 422 for a missing child entity */
    @ExceptionHandler(EntityNotFoundException)
    @ResponseBody
    ResponseEntity<ErrorResponse> handle(EntityNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(message: e.message), status)
    }

    /** This happens when a null is returned to any of the controllers */
    @ExceptionHandler(ServletException)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ErrorResponse handle(ServletException e) {
        log.warn('Null result error: {}', e.message)
        return new ErrorResponse(message: e.message)
    }

    @ExceptionHandler(ConstraintViolationException)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    ErrorResponse handle(ConstraintViolationException e) {
        return new ErrorResponse(
                message: 'Error saving entity'
        )
    }

}
