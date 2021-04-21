package handler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

class HandlerUtils {
    public static Map<String, String> queryToMap(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }
        return Pattern.compile("&").splitAsStream(query)
                .map(s -> Arrays.copyOf(s.split("="), 2))
                .collect(HashMap::new, (result, value) -> result.put(value[0], value[1]), HashMap::putAll);
    }

    public static boolean isLastnameValid(String lastname) {
        return Pattern.compile("[a-zA-Z]{3,255}").matcher(lastname).matches();
    }
}
