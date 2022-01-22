package ua.com.alevel.hw_8_web_jdbc.exception;

import java.util.HashMap;
import java.util.Map;

public class PageNotExistException extends RuntimeException {

    private final Map<String, String> params;

    public PageNotExistException(String message) {
        super(message);
        params = new HashMap<>();
    }

    public PageNotExistException(String message, Map<String, String> params) {
        super(message);
        this.params = params;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
