package api.request.impl;

import api.request.HttpRequestImpl;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteRequestImpl extends HttpRequestImpl {


    @Override
    public Response request() {
        Response response;
        try {
            response = given()
                    .header("X-Auth-Token", "5049504")
                    .contentType("application/json")
                    .when()
                    .delete(getUrl());
        } catch (Exception exception) {

            throw exception;
        }
        return response;
    }
}
