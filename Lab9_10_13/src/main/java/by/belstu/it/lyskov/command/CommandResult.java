package by.belstu.it.lyskov.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public class CommandResult {
    private static final String DEFAULT = "/";
    private String path;
    private Map<String, Object> responseData;

    public CommandResult() {
        path = DEFAULT;
    }

    public CommandResult(String path) {
        this.path = path;
    }

    public CommandResult(Map<String, Object> responseData) {
        this.responseData = responseData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Map<String,Object> responseData) {
        this.responseData = responseData;
    }
}