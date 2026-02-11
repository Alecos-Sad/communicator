package by.sadovnick.communicator.advice;

import by.sadovnick.communicator.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static by.sadovnick.communicator.enums.ErrorStatus.DATABASE_SQL_ERROR;
import static by.sadovnick.communicator.enums.ErrorStatus.VALIDATION_ERROR;
import static by.sadovnick.communicator.enums.JsonLogField.TRACE_ID;

@Slf4j
public class AdminCommonControllerAdvice {

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

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ErrorResponse> handleIllegalArgumentException(final IllegalArgumentException e) {
        ErrorResponse errorResponse = getValidationErrorResponse(e);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    public ErrorResponse getValidationErrorResponse(Exception e) {
        return new ErrorResponse(
                VALIDATION_ERROR.getCode(),
                VALIDATION_ERROR.getDescription(),
                VALIDATION_ERROR.getMessage(),
                MDC.get(TRACE_ID.getDescription())
        );
    }
}
