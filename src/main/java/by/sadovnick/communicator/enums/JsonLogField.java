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
    HTTP_METHOD("httpMethod"),
    REQUEST("request"),
    RESPONSE("response"),
    CLIENT_UUID("clientUUID");

    private final String description;

    JsonLogField(String description) {
        this.description = description;
    }
}
