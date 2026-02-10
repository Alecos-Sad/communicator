package by.sadovnick.communicator.advice;

import by.sadovnick.communicator.controller.ApplicationController;
import by.sadovnick.communicator.dto.ErrorResponse;
import by.sadovnick.communicator.enums.ErrorStatus;
import by.sadovnick.communicator.enums.JsonLogField;
import by.sadovnick.communicator.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static by.sadovnick.communicator.enums.ErrorStatus.*;
import static by.sadovnick.communicator.enums.JsonLogField.*;

@ControllerAdvice(
        assignableTypes = {
                ApplicationController.class
        }
)
@Slf4j
public class ApplicationControllerAdvice {

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> handleBusinessLogicException(final BusinessLogicException e) {
        return ResponseEntity.badRequest().body(e.getErrorResponse());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(final DataIntegrityViolationException e) {

        ErrorResponse errorResponse = new ErrorResponse(
                DATABASE_SQL_ERROR.getCode(),
                DATABASE_SQL_ERROR.getDescription(),
                DATABASE_SQL_ERROR.getMessage(),
                MDC.get(TRACE_ID.getDescription()));
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
