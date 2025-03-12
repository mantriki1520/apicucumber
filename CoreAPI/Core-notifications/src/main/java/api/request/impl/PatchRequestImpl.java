package api.request.impl;

import api.request.HttpRequestImpl;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PatchRequestImpl extends HttpRequestImpl {
    @Override
    public Response request() {
        Response response;
        try {
            response = given()
                    .header("X-Auth-Token", "5049504")
                    .contentType("application/json")
                    .body(getPayload())
                    .log()
                    .all()
                    .when()
                    .patch(getUrl());
        } catch (Exception exception) {
            throw exception;
            //throw new RuntimeException("invalid request");
        }
        return response;
    }
}
