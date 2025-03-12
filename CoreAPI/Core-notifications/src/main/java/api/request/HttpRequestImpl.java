package api.request;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HttpRequestImpl {

    @Getter
    private String url;
    @Getter
    private String token;
    @Getter
    private File filePayload;
    @Getter
    private String payload;
    @Getter
    private HashMap<String, String> queryParams;

    public abstract Response request();

    public HttpRequestImpl setUrl(String url) {

        this.url = url;
        return this;
    }

    public HttpRequestImpl setToken(String token) {
        this.token = token;
        return this;
    }

    public HttpRequestImpl setPayload(File filePayload) {
        this.filePayload = filePayload;
        return this;
    }

    public HttpRequestImpl setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public HttpRequestImpl setQueryParams(HashMap<String, String> queryParams) {
        this.queryParams = queryParams;
        return this;
    }


}
