package api.request.impl;

import api.request.HttpRequestImpl;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetRequestQueryImpl extends HttpRequestImpl {

    @Override
    public Response request() {
        Response response;
        try {
            response = given()
                            .header("auth", "")
                            .log()
                            .all()
                            .contentType("application/json")
                            .queryParams(getQueryParams())
                            .when()
                            .get(getUrl());
        } catch (Exception exception) {

            //throw new RuntimeException("invalid request");
            throw exception;
        }
        return response;
    }
}
