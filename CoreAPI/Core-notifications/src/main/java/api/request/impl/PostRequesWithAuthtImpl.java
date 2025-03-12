package api.request.impl;

import api.request.HttpRequestImpl;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostRequesWithAuthtImpl extends HttpRequestImpl {
    @Override
    public Response request() {
        Response response;
        try {
            response = given()
                    .header("Authorization", getToken())
                    .contentType("application/json")
                    .body(getPayload())
                    .log()
                    .all()
                    .when()
                    .post(getUrl());
        } catch (Exception exception) {
            throw exception;
            //throw new RuntimeException("invalid request");
        }
        return response;
    }
}
