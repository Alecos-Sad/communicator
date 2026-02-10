package by.sadovnick.communicator.util;

import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class RequestUtil {

    private static final ThreadLocal<String> requestPath = new ThreadLocal<>();
    private static final ThreadLocal<String> clientUUID = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, String>> headers = new ThreadLocal<>();

    public static void setHeaders(Map<String, String> headerMap) {
        RequestUtil.headers.set(headerMap);
    }

    public static Map<String, String> getHeaders() {
        return RequestUtil.getHeaders();
    }

    public static void setClientUUID(String clientUUID) {
        RequestUtil.clientUUID.set(clientUUID);
    }

    public static String getClientUUID() {
        return clientUUID.get();
    }

    public static void setRequestPath(String requestPath) {
        RequestUtil.requestPath.set(requestPath);
    }

    public static String getRequestPath(){
        return requestPath.get();
    }

    public static void clear() {
        requestPath.remove();
        clientUUID.remove();
        headers.remove();
    }
}
