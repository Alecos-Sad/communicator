package by.sadovnick.communicator.exception;

import by.sadovnick.communicator.dto.ErrorResponse;
import by.sadovnick.communicator.enums.ErrorStatus;
import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {

    private ErrorResponse errorResponse;
    private ErrorStatus errorStatus;

    public BusinessLogicException(ErrorResponse errorResponse) {
        super(errorResponse.getMessage());
        this.errorResponse = errorResponse;
    }

    public BusinessLogicException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public BusinessLogicException(String message) {
        super(message);
    }
}
