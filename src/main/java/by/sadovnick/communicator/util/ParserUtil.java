package by.sadovnick.communicator.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class ParserUtil {

    public static String getCNFromDN(String clientDN) {
        List<String> pairs = Arrays
                .stream(clientDN.strip().split(","))
                .filter(el -> el.contains("="))
                .map(String::strip)
                .toList();
        String result;
        if (pairs.isEmpty()) {
            return null;
        } else {
            Map<String, String> map = new HashMap<>();
            for (String pair : pairs) {
                String[] keyValuePair = pair.split("=", 2);
                map.put(keyValuePair[0].strip(), keyValuePair[1].strip());
            }
            result = map.get("CN");
        }
        return result;
    }

    public static String parseListToStringWithOutBrackets(List<String> list) {
        if (list != null && !list.isEmpty()) {
            return list
                    .stream()
                    .filter(s -> !StringUtils.isBlank(s))
                    .map(String::strip)
                    .collect(Collectors.joining(","));
        }
        return null;
    }
}