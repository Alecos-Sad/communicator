package by.sadovnick.communicator.advice;

import by.sadovnick.communicator.dto.ErrorResponse;
import by.sadovnick.communicator.enums.SubSystem;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
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
import static by.sadovnick.communicator.enums.JsonLogField.*;
import static by.sadovnick.communicator.util.RequestUtil.getRequestPath;
import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
public class AdminCommonControllerAdvice {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(final DataIntegrityViolationException e) {
        Throwable root = rootCause(e);

        String constraint = null;
        String detail = null;
        String table = null;
        String sqlState = null;

        if (root instanceof PSQLException psql && psql.getServerErrorMessage() != null) {
            ServerErrorMessage sem = psql.getServerErrorMessage();
            constraint = sem.getConstraint();
            detail = sem.getDetail();
            table = sem.getTable();
            sqlState = psql.getSQLState();
        }
        log.error(DATABASE_SQL_ERROR.name(),
                kv(ERROR.getDescription(), DATABASE_SQL_ERROR.getDescription()),
                kv(ERROR_TYPE.getDescription(), root.getClass().getSimpleName()),
                kv(SQL_STATE.getDescription(), sqlState),
                kv(CONSTRAINT.getDescription(), constraint),
                kv(TABLE.getDescription(), table),
                kv(DETAIL.getDescription(), detail),
                kv(REQUEST_PATH.getDescription(), getRequestPath()),
                kv(SUB_SYSTEM.getDescription(), SubSystem.ADMIN.getDescription()));
        ErrorResponse errorResponse = new ErrorResponse(
                DATABASE_SQL_ERROR.getCode(),
                DATABASE_SQL_ERROR.getDescription(),
                DATABASE_SQL_ERROR.getMessage(),
                MDC.get(TRACE_ID.getDescription()));
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
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

    private static Throwable rootCause(Throwable t) {
        Throwable r = t;
        while (r.getCause() != null && r.getCause() != r) {
            r = r.getCause();
        }
        return r;
    }
}
