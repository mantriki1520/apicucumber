package api.request.impl;

import api.request.HttpRequestImpl;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostRequestImpl extends HttpRequestImpl {
    @Override
    public Response request() {
        Response response;
        try {
            //     RestAssured.baseURI="https://bookstore.toolsqa.com";
            response = given()
                    .header("X-Auth-Token", "5049504")
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
