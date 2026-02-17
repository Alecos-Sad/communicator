package by.sadovnick.communicator.utils;

import by.sadovnick.communicator.util.RequestUtil;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

public class RequestUtilTest {

    private Map<String, String> headerMap;

    @BeforeEach
    void setHeaderMap(){
        headerMap = new HashMap<>();
        headerMap.put("header-key", "header-value");
    }

    @AfterEach
    void clear() {
        RequestUtil.clear();
    }

    @Test
    void setAndGetHeaders() {
        RequestUtil.setHeaders(headerMap);
        Map<String, String> headers = RequestUtil.getHeaders();
        Assertions.assertEquals("header-value", headers.get("header-key"));
    }

    @Test
    void setAndGetRequestPathTest() {
        String requestPath = "/v1/application";
        RequestUtil.setRequestPath(requestPath);
        Assertions.assertEquals(requestPath, RequestUtil.getRequestPath());
    }

    @Test
    void setAndGetClientUUIDTest() {
        String clientUUID = "CN=CERT-11";
        RequestUtil.setClientUUID(clientUUID);
        Assertions.assertEquals(clientUUID, RequestUtil.getClientUUID());
    }
}
