package by.sadovnick.communicator.enums;

import lombok.Getter;

@Getter
public enum JsonLogField {
    CLIENT_DN("clientDN"),
    SUB_SYSTEM("subSystem"),
    SERVLET_PATH("servletPath"),
    TRACE_ID("traceId"),
    CN("cn"),
    ERROR("error"),
    ERROR_TYPE("errorType"),
    HTTP_METHOD("httpMethod"),
    REQUEST("request"),
    RESPONSE("response"),
    CLIENT_UUID("clientUUID"),
    REQUEST_PATH("requestPath"),
    SQL_STATE("sqlState"),
    CONSTRAINT("constraint"),
    TABLE("table"),
    DETAIL("detail");

    private final String description;

    JsonLogField(String description) {
        this.description = description;
    }
}
