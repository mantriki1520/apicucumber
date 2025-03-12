package api.request.impl;

import api.request.HttpRequestImpl;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetRequestImpl extends HttpRequestImpl {

    @Override
    public Response request() {
        Response response;
        try {
            response = given()
                    .header("", "")
                    .contentType("application/json")
                    .when()
                    .get(getUrl());
        } catch (Exception exception) {
            throw exception;
            //throw new RuntimeException("invalid request");
        }
        return response;
    }
}
