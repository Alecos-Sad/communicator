package by.sadovnick.communicator.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class ParserUtil {

    //TODO оптимизация и тесты
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
                String[] keyValuePair = pair.split("=");
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
                    .map(String::strip)
                    .collect(Collectors.joining(","));
        }
        return null;
    }
}
