package by.sadovnick.communicator.utils;

import by.sadovnick.communicator.util.ParserUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParserUtilsTest {

    private static final String goodDN = "C = RU, ST = Moscow, L = Moscow, O = Sber, OU = SberUser, CN = CERT-11";
    private static final String goodDN2 = "C=RU, ST=Moscow, L=Moscow, O=Sber, OU=SberUser, CN=CERT-11";
    private static final String goodDN3 = "C=RU,ST=Moscow,L=Moscow,O=Sber,OU=SberUser,CN=CERT-11";
    private static final String badDN = "C=RU,ST=Moscow,L=Moscow,O=Sber,OU=SberUser";
    private static final String badDN2 = "";
    private static final String badDN3 = "C=RU,ST=Moscow,L=Moscow,O=Sber,OU=SberUser,CN=";


    @ParameterizedTest(name = "{index} => dn={0}")
    @MethodSource("goodDNProvider")
    void getCNFromGoodDN(String dn) {
        assertEquals("CERT-11", ParserUtil.getCNFromDN(dn));
    }

    @ParameterizedTest(name = "{index} => dn={0}")
    @MethodSource("badDNProvider")
    void getCNFromBadDN(String dn) {
        assertNull(ParserUtil.getCNFromDN(dn));
    }

    @Test
    void getCNFromDNReturnsNullForMalformedEmptyCNSegment() {
        assertEquals("", ParserUtil.getCNFromDN(badDN3));
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

    @Test
    void parseListToStringWithoutBracketsWhenListIsNull() {
        assertNull(ParserUtil.parseListToStringWithOutBrackets(null));
    }

    @Test
    void parseListWithoutBracketsWhenListIsEmpty() {
        assertNull(ParserUtil.parseListToStringWithOutBrackets(List.of()));
    }

    private static Stream<Arguments> goodDNProvider() {
        return Stream.of(
                Arguments.of(goodDN),
                Arguments.of(goodDN2),
                Arguments.of(goodDN3)
        );
    }

    private static Stream<Arguments> badDNProvider() {
        return Stream.of(
                Arguments.of(badDN),
                Arguments.of(badDN2)
        );
    }

}
