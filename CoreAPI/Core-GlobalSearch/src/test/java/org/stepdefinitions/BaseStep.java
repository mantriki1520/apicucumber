package org.stepdefinitions;

import Utilities.BaseUtilities;
import api.request.HttpRequestImpl;
import api.request.impl.GetRequestQueryImpl;
import api.request.impl.PostRequestImpl;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;


public class BaseStep {


    private static final Log log = LogFactory.getLog(BaseStep.class);
    private HttpRequestImpl httpRequestImpl;
    private String token;
    private Response response;
    private JsonPath jsonPath;
    private String baseUrl;
    private BaseUtilities baseUtilities = new BaseUtilities();
    private HashMap<String, String> queryParams = new HashMap<>();
    private static List<String> firstGETGSAPIHitResidsForEvents;
    private static List<String> firstGETGSAPIHitResidsForGroups;
    private static List<String> firstGETGSAPIHitResidsForUsers;
    private static List<String> firstGETGSAPIHitResidsForCourse;
    private static List<String> firstGETGSAPIHitResidsForJobs;
    private static List<String> firstGETGSAPIHitResidsForEntities;

    @When("I send a GET-API request to {string}")
    public void iMakeAGetRequest(String endpoint) throws IOException {

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = baseUtilities.getBaseUrl("baseUrl");
        // Add query parameters to hashmap

        httpRequestImpl = new GetRequestQueryImpl();
        response = httpRequestImpl.setQueryParams(queryParams).setUrl(endpoint).request();
        response.then().log().all();

        //scenario.getContext().set("responsePojo", responsePojo);
    }

    @When("I create {string} payload")
    public void iCreatePayload(String arg0) {
    }


    @When("I set {string} value as {string}")
    public void iSetBelowValuesFrom(String expectedField, String expectedValue) {


    }

    @When("I set below {string} field values")
    public void iSetBelowValuesFrom(String controllerType, DataTable dt) {


    }


    @And("I check response status code is {string}")
    public void iCheckResponseStatusCode(String expectedStatusCode) {

        assertThat("Response Status Code :-" + response.getStatusCode() + " is not matching with Expected Status Code :- " + expectedStatusCode, response.getStatusCode() == Integer.parseInt(expectedStatusCode));


    }

    @And("I validate below response details")
    public void iValidateBelowResponseDetails(DataTable dt) {

        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        System.out.println(map.get(0).values());
        System.out.println("Hi");
//     for(Map.Entry<String,String> map1:map.entrySet()){
//         System.out.println(map1.getKey());
//         System.out.println(map1.getValue());

    }


    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int arg0) {
    }

    @When("I set Query Params For this API")
    public void iSetQueryParamsForThisAPI(DataTable dt) {
        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        System.out.println(map.get(0).keySet());
        for (Map<String, String> m : map) {
            for (Map.Entry<String, String> data : m.entrySet()) {
                System.out.println("Key = " + data.getKey() + ",Value = " + data.getValue());
                queryParams.put(data.getKey(), data.getValue());
            }
        }
    }

    @And("I validate {string} field {string} json objects are fetching for GET Global Search API")
    public void iValidateExpectedJsonObjectsAreGettingForForGETGlobalSearchAPI(String module, String jsonObjectsValue) {

        if (response.getStatusCode() == Integer.parseInt("200")) {
            //validating for EVENTS module 2 json objects will get
            response.then().body(module + ".size()", equalTo(Integer.parseInt(jsonObjectsValue)));
        }
    }

    @And("I validate empty response")
    public void iValidateEmptyResponse() {
        response.then().assertThat().body(equalTo("{}"));
    }

    @And("I validate response")
    public void iValidateResponse() {
        response.then().assertThat().body(notNullValue());
    }

    @Given("I have navigated to {string}")
    public void theApiBaseUrlIsProvided(String service) throws IOException {
        String baseUrl = baseUtilities.getBaseUrl("baseUrl");
        if (service.contains("Global")) {
            Assert.assertTrue(baseUrl.contains("gs-qas"));
        } else if (service.contains("Notification")) {
            Assert.assertTrue(baseUrl.contains("npr-eus"));
        } else {
            Assert.assertTrue(baseUrl.contains("npr-eus"));
        }
    }

    @And("I validate response for Get Global Search API contains {string} Json Objects")
    public void iValidateResponseForGetGlobalSearchAPIContainsJsonObjects(String jsobObjectValue) {

        //  Validate 'EVENTS' if present and ensure its size
        baseUtilities.validateJsonArraySize(response, "EVENTS", jsobObjectValue);

        // Validate 'GROUPS' if present and ensure its size
        baseUtilities.validateJsonArraySize(response, "GROUPS", jsobObjectValue);

        // Validate 'USERS' if present and ensure its size
        baseUtilities.validateJsonArraySize(response, "USERS", jsobObjectValue);

        // Validate 'COURSES' if present and ensure its size
        baseUtilities.validateJsonArraySize(response, "COURSES", jsobObjectValue);

        // Validate 'JOBS' if present and ensure its size
        baseUtilities.validateJsonArraySize(response, "JOBS", jsobObjectValue);

        // Validate 'ENTITIES' if present and ensure its size
        baseUtilities.validateJsonArraySize(response, "ENTITIES", jsobObjectValue);

        //extracting ids from each jsonArrayObject for 1st Hit

        firstGETGSAPIHitResidsForEvents = response.jsonPath().getList("EVENTS.id");
        firstGETGSAPIHitResidsForGroups = response.jsonPath().getList("GROUPS.user_id");
        firstGETGSAPIHitResidsForUsers = response.jsonPath().getList("USERS.user_id");
        firstGETGSAPIHitResidsForCourse = response.jsonPath().getList("COURSE.courseId");
        firstGETGSAPIHitResidsForJobs = response.jsonPath().getList("JOBS.id");
        firstGETGSAPIHitResidsForEntities = response.jsonPath().getList("ENTITIES.userId");

    }

    @And("I validate the response body for the correct data based on the skip and top query parameters")
    public void iValidateTheResponseBodyForTheCorrectDataBasedOnTheSkipAndTopQueryParameters() {
        //extracting ids from each jsonArrayObject for 2nd Hit
        List<String> secondGETGSAPIHitResidsForEvents = response.jsonPath().getList("EVENTS.id");
        List<String> secondGETGSAPIHitResidsForGroups = response.jsonPath().getList("GROUPS.user_id");
        List<String> secondGETGSAPIHitResidsForUsers = response.jsonPath().getList("USERS.user_id");
        List<String> secondGETGSAPIHitResidsForCourse = response.jsonPath().getList("COURSE.courseId");
        List<String> secondGETGSAPIHitResidsForJobs = response.jsonPath().getList("JOBS.id");
        List<String> secondGETGSAPIHitResidsForEntites = response.jsonPath().getList("ENTITIES.userId");

        if ((secondGETGSAPIHitResidsForEvents != null && !secondGETGSAPIHitResidsForEvents.isEmpty()) && (firstGETGSAPIHitResidsForEvents != null && !firstGETGSAPIHitResidsForEvents.isEmpty()))
            Assert.assertFalse("skip Events json objections should not display", secondGETGSAPIHitResidsForEvents.containsAll(firstGETGSAPIHitResidsForEvents));
        if ((secondGETGSAPIHitResidsForGroups != null && !secondGETGSAPIHitResidsForGroups.isEmpty()) && (firstGETGSAPIHitResidsForGroups != null && !firstGETGSAPIHitResidsForGroups.isEmpty()))
            Assert.assertFalse("skip Groups json objections should not display", secondGETGSAPIHitResidsForGroups.containsAll(firstGETGSAPIHitResidsForGroups));
        if ((secondGETGSAPIHitResidsForUsers != null && !secondGETGSAPIHitResidsForUsers.isEmpty()) && (firstGETGSAPIHitResidsForUsers != null && !firstGETGSAPIHitResidsForUsers.isEmpty()))
            Assert.assertFalse("skip Users json objections should not display", secondGETGSAPIHitResidsForUsers.containsAll(firstGETGSAPIHitResidsForUsers));
        if ((secondGETGSAPIHitResidsForCourse != null && !secondGETGSAPIHitResidsForCourse.isEmpty()) && (firstGETGSAPIHitResidsForCourse != null && !firstGETGSAPIHitResidsForCourse.isEmpty()))
            Assert.assertFalse("skip Course json objections should not display", secondGETGSAPIHitResidsForCourse.containsAll(firstGETGSAPIHitResidsForCourse));
        if ((secondGETGSAPIHitResidsForJobs != null && !secondGETGSAPIHitResidsForJobs.isEmpty()) && (firstGETGSAPIHitResidsForJobs != null && !firstGETGSAPIHitResidsForJobs.isEmpty()))
            Assert.assertFalse("skip Users json objections should not display", secondGETGSAPIHitResidsForJobs.containsAll(firstGETGSAPIHitResidsForJobs));
        if ((secondGETGSAPIHitResidsForEntites != null && !secondGETGSAPIHitResidsForEntites.isEmpty()) && (firstGETGSAPIHitResidsForEntities != null && !firstGETGSAPIHitResidsForEntities.isEmpty()))
            Assert.assertFalse("skip Course json objections should not display", secondGETGSAPIHitResidsForEntites.containsAll(firstGETGSAPIHitResidsForEntities));



    }
}


