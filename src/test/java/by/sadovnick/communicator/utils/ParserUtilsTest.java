package by.sadovnick.communicator.utils;

import by.sadovnick.communicator.util.ParserUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserUtilsTest {

    @Test
    void getCNFromDNReturnsNullForMalformedEmptyCNSegment() {
        assertEquals("",ParserUtil.getCNFromDN("CN="));
    }

    @Test
    void parseListToStringWithoutBracketsSkipsNullElement() {
        assertEquals("first,second", ParserUtil.parseListToStringWithOutBrackets(List.of("first", " second  ")));
        List<String> el = new ArrayList<>();
        el.add("first");
        el.add(null);
        el.add("second");
        el.add(null);
        assertEquals("first,second", ParserUtil.parseListToStringWithOutBrackets(el));
    }

}
