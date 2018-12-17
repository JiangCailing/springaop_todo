package com.todo.demo.config;

import com.todo.demo.aspects.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {
    
     private static Logger log=LoggerFactory.getLogger(ExceptionHandlers.class);
    
    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(final ServletRequestBindingException ex) {
        log.error("Missing parameter", ex);
        return new ErrorResponse("MISSING_PARAMETER", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handle(final IllegalArgumentException ex) {
        log.error("Missing parameter", ex);
        return new ErrorResponse("ILLEGAL_ARGUMENT", ex.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN    )
    @ResponseBody
    public ErrorResponse handle(final ForbiddenException ex) {
        log.error("Missing parameter", ex);
        return new ErrorResponse("FORBIDDEN_ACTION", ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handle(final Throwable ex) {
        log.error("Unexpected error", ex);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected internal server error occured");
    }


    public static class ErrorResponse {
        private final String code;
        private final String message;

        public ErrorResponse(String code, String message) {
            this.code = code;
            this.message = message;
        }
        
        
    }
}
