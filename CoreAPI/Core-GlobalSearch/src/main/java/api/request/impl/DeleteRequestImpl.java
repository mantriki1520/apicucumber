package api.request.impl;

import api.request.HttpRequestImpl;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DeleteRequestImpl extends HttpRequestImpl {


    @Override
    public Response request() {
        Response response;
        try {
            response = given().header("", "").contentType("application/json").when().delete(getUrl());
        } catch (Exception ex) {

            throw new RuntimeException("invalid request");
        }
        return response;
    }
}
