package by.sadovnick.communicator.utils;

import by.sadovnick.communicator.util.RequestUtil;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

public class RequestUtilTest {

    private Map<String, String> headerMap;

    private final String REQUEST_PATH = "/v1/application";
    private final String CLIENT_UUID = "CERT-11";

    @BeforeEach
    void setHeaderMap(){
        headerMap = new HashMap<>();
        headerMap.put("header-key", "header-value");
    }

    @AfterEach
    void tearDown() {
        RequestUtil.clear();
    }

    @Test
    void setAndGetHeaders() {
        RequestUtil.setHeaders(headerMap);
        Map<String, String> headers = RequestUtil.getHeaders();
        Assertions.assertEquals("header-value", headers.get("header-key"));
        Assertions.assertNull(headers.get("no-header-key"));
    }

    @Test
    void setAndGetRequestPathTest() {
        RequestUtil.setRequestPath(REQUEST_PATH);
        Assertions.assertEquals(REQUEST_PATH, RequestUtil.getRequestPath());
    }

    @Test
    void setAndGetClientUUIDTest() {
        RequestUtil.setClientUUID(CLIENT_UUID);
        Assertions.assertEquals(CLIENT_UUID, RequestUtil.getClientUUID());
    }

    @Test
    void clearAllThreadLocalValuesTest(){
        RequestUtil.setRequestPath(REQUEST_PATH);
        RequestUtil.setClientUUID(CLIENT_UUID);
        RequestUtil.clear();
        Assertions.assertNull(RequestUtil.getRequestPath());
        Assertions.assertNull(RequestUtil.getClientUUID());
    }

}
