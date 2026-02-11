package by.sadovnick.communicator.advice;

import by.sadovnick.communicator.controller.ApplicationController;
import by.sadovnick.communicator.dto.ErrorResponse;
import by.sadovnick.communicator.exception.BusinessLogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(
        assignableTypes = {
                ApplicationController.class
        }
)
@Slf4j
public class ApplicationControllerAdvice extends AdminCommonControllerAdvice{

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> handleBusinessLogicException(final BusinessLogicException e) {
        return ResponseEntity.badRequest().body(e.getErrorResponse());
    }
}
