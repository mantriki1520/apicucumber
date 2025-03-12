package org.stepdefinitions;

import Utilities.BaseUtilities;
import api.request.HttpRequestImpl;
import api.request.impl.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.CreateUserProfileReqSpecification;
import model.NotificationsServiceReqSpecification;
import model.UserNotificationReqSpecification;
import model.factory.NotificationServiceRequestType;
import model.factory.UserServiceRequestType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.*;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;


public class BaseStep {


    private static final Log log = LogFactory.getLog(BaseStep.class);
    private HttpRequestImpl httpRequestImpl;
    private String token;
    private Response createUserProfileResponse;
    private Response response;
    private JsonPath jsonPath;
    private String baseUrl;
    private BaseUtilities baseUtilities = new BaseUtilities();
    private HashMap<String, String> queryParams = new HashMap<>();
    private NotificationServiceRequestType notificationServiceRequestType;
    private NotificationsServiceReqSpecification notificationServiceReqSpecification = NotificationsServiceReqSpecification.builder().build();
    private UserServiceRequestType userServiceRequestType;
    private CreateUserProfileReqSpecification userServiceReqSpecification = CreateUserProfileReqSpecification.builder().build();
    private UserNotificationReqSpecification userNotificationReqSpecification = UserNotificationReqSpecification.builder().build();
    private String payload;
    private String userId;
    private static String expectedUserNotificationId;

    @When("I send a GET-API request to {string}")
    public void iMakeAGetRequest(String apiEndPoint) throws IOException {

        String endPoint = null;
        if (apiEndPoint.contains("{id}")) {
            endPoint = apiEndPoint.replace("{id}", userId);
        } else if (apiEndPoint.contains("{userId}")) {
            endPoint = apiEndPoint.replace("{userId}", userId);
        } else {
            endPoint = apiEndPoint;
        }

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = baseUtilities.getBaseUrl("baseUrl");
        // Add query parameters to hashmap
        if (apiEndPoint.equalsIgnoreCase("/v1/usernotifications")) {
            httpRequestImpl = new GetRequestQueryImpl();
            response = httpRequestImpl.setQueryParams(queryParams).setUrl(endPoint).request();
            response.then().log().all();
        } else {
            httpRequestImpl = new GetRequestImpl();
            response = httpRequestImpl.setUrl(endPoint).request();
            response.then().log().all();
        }

    }


    @And("I check response status code is {string} for {string}")
    public void iCheckResponseStatusCode(String expectedStatusCode, String service) {

        if (service.equalsIgnoreCase("user services"))
            assertThat("Response Status Code :-" + createUserProfileResponse.getStatusCode() + " is not matching with Expected Status Code :- " + expectedStatusCode, createUserProfileResponse.getStatusCode() == Integer.parseInt(expectedStatusCode));
        else if (service.equalsIgnoreCase("notification services"))
            assertThat("Response Status Code :-" + response.getStatusCode() + " is not matching with Expected Status Code :- " + expectedStatusCode, response.getStatusCode() == Integer.parseInt(expectedStatusCode));

    }

    @And("I validate below response details")
    public void iValidateBelowResponseDetails(DataTable dt) {
        HashMap<String, String> actualValuesMap = new HashMap<>();
        List<Map<String, String>> map = dt.asMaps(String.class, String.class);
        System.out.println(map.get(0).values());
        for (Map<String, String> m : map) {
            for (Map.Entry<String, String> data : m.entrySet()) {
                System.out.println("Key = " + data.getKey() + ",Value = " + data.getValue());
                //actualValuesMap.put(data.getKey(), data.getValue());
                Assert.assertTrue(response.jsonPath().get(data.getKey()).equals(data.getValue()));
            }
        }

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


    @And("I have {string} Request Type")
    public void iHaveRequestType(String requestType) {
        if (requestType.equalsIgnoreCase("Post Event Notification"))
            notificationServiceRequestType = NotificationServiceRequestType.selectController(requestType);
        else if (requestType.equalsIgnoreCase("Post User Profile"))
            userServiceRequestType = UserServiceRequestType.selectController(requestType);
        else if (requestType.equalsIgnoreCase("Update User Notification"))
            notificationServiceRequestType = NotificationServiceRequestType.selectController(requestType);
    }

    @When("I set userEmail field to {string}")
    public void iSetUserEmailFieldTo(String userEmail) {
        notificationServiceReqSpecification.setEmail(userEmail);
        System.out.println("User Email Field is set");
    }


    @And("I create my {string} request for {string}")
    public void iCreateMyRequest(String requestType, String request) throws IOException {
        if (request.equalsIgnoreCase("Notification Services")) {
            if (requestType.equalsIgnoreCase("Valid")) {
                notificationServiceReqSpecification.toBuilder().build();
                ObjectMapper mapper = new ObjectMapper();
                payload = mapper.writeValueAsString(notificationServiceRequestType.build(notificationServiceReqSpecification));
            } else if ((requestType.equalsIgnoreCase("Invalid"))) {
                payload = "";
            }
        } else if (request.equalsIgnoreCase("Update User Notification")) {
            if (requestType.equalsIgnoreCase("Valid")) {
                userNotificationReqSpecification.toBuilder().build();
                ObjectMapper mapper = new ObjectMapper();
                payload = mapper.writeValueAsString(notificationServiceRequestType.build(userNotificationReqSpecification));
            } else if ((requestType.equalsIgnoreCase("Invalid"))) {
                payload = "";
            }
        } else if (request.equalsIgnoreCase("Post User Profile")) {
            if (requestType.equalsIgnoreCase("Valid")) {
                userServiceReqSpecification.toBuilder().build();
                ObjectMapper mapper = new ObjectMapper();
                payload = mapper.writeValueAsString(userServiceRequestType.build(userServiceReqSpecification));
            } else if ((requestType.equalsIgnoreCase("Invalid"))) {
                payload = "";
            }
        }
    }

    @And("I send POST Request to {string}")
    public void iSendPOSTRequestTo(String apiEndPoint) throws IOException {
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = baseUtilities.getBaseUrl("baseUrl");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(notificationServiceRequestType.build(notificationServiceReqSpecification));
        if (apiEndPoint.contains("notifications")) {
            String notificationPayload = "[" + json + "]";
            httpRequestImpl = new PostRequestImpl();
            response = httpRequestImpl.setPayload(notificationPayload).setUrl(apiEndPoint).request();
            String jsonString = response.asString();
            System.out.println("POST Response:-" + jsonString);
        } else {
            httpRequestImpl = new PostRequestImpl();
            response = httpRequestImpl.setPayload(json).setUrl(apiEndPoint).request();
            String jsonString = response.asString();
            System.out.println("POST Response:-" + jsonString);
        }

    }

    @And("I validate {string} response Message")
    public void iValidateResponse(String responseMsg) {
        Assert.assertTrue(response.asString().contains(responseMsg));
    }


    @And("I have Generated token Using Credentials {string}")
    public void iHaveGeneratedTokenUsingCredentials(String userName) {
        String access_token = baseUtilities.generateToken(userName);
        token = "Bearer " + access_token;
    }

    @And("I have {string} Guid as userId")
    public void iHaveValidGuidAsUserId(String type) {
        if (Objects.equals(type, "valid"))
            userId = UUID.randomUUID().toString();
        else if (Objects.equals(type, "invalid"))
            userId = "3e95bdb0-73d9";
        else if (Objects.equals(type, "blank"))
            userId = "";
        else if (Objects.equals(type, "existing userId")) {
            userId = baseUtilities.getValidExistingUserId("existing_userId");
        }
    }

    @And("I send POST Request to {string} for User Services")
    public void iSendPOSTRequestToUserServices(String apiEndPoint) throws IOException {
        String endPoint = null;
        if (apiEndPoint.contains("{id}")) {
            endPoint = apiEndPoint.replace("{id}", userId);
        } else {
            endPoint = apiEndPoint;
        }
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = baseUtilities.getBaseUrl_UserService("UserServicebaseUrl");
        httpRequestImpl = new PostRequesWithAuthtImpl();
        createUserProfileResponse = httpRequestImpl.setPayload(payload).setToken(token).setUrl(endPoint).request();
        String jsonString = createUserProfileResponse.asString();
        System.out.println("POST Response:-" + jsonString);
    }


    @When("I set {string} Guid as userId")
    public void iSetGuidAsUserId(String type) {
        if (Objects.equals(type, "valid"))
            userId = createUserProfileResponse.jsonPath().get("userId").toString();
        else if (Objects.equals(type, "unknown"))
            userId = "3e95bdb0-73d9-438a-8684-b049ff9d6a9";
        else if (Objects.equals(type, "invalid"))
            userId = "3e95bdb0-73d9";
    }


    @And("I set {string} Query Params from the user profile response")
    public void iSetQueryParamsFromTheResponse(String query) {

        if (query.equalsIgnoreCase("userId")) {
            String userId = createUserProfileResponse.jsonPath().get("userId");
            queryParams.put("userId", userId);
        } else if (query.equalsIgnoreCase("existing userId")) {
            String userId = baseUtilities.getValidExistingUserId("existing_userId");
            queryParams.put("userId", userId);
        }

    }

    @And("I Validate expected {string} present in response for User Notifications")
    public void iValidateExpectedIdPresentInResponse(String value) throws Exception {
        if (value.equalsIgnoreCase("userId")) {
            List<String> actualUserIds = response.jsonPath().get("content.userId");
            String expectedUserId = createUserProfileResponse.jsonPath().get("userId");
            boolean allMatch = actualUserIds.stream().allMatch(actualUserId -> actualUserId.equals(expectedUserId));
            assertThat(allMatch, is(true));
        } else if (value.equalsIgnoreCase("exiting userId")) {
            List<String> actualUserIds = response.jsonPath().get("content.userId");
            String expectedUserId = baseUtilities.getValidExistingUserId("existing_userId");
            boolean allMatch = actualUserIds.stream().allMatch(actualUserId -> actualUserId.equals(expectedUserId));
            assertThat(allMatch, is(true));
        } else if (value.equalsIgnoreCase("user notification Id")) {
            String actualId = response.jsonPath().get("id");
            expectedUserNotificationId = userId;
            Assert.assertTrue(actualId.equalsIgnoreCase(expectedUserNotificationId));
        } else if (value.equalsIgnoreCase("existing user notification Id")) {
            String actualId = response.jsonPath().get("id");
            expectedUserNotificationId = baseUtilities.getValidExistingUserNotificationId("existing_notification_id");
            Assert.assertTrue(actualId.equalsIgnoreCase(expectedUserNotificationId));
        }

    }

    @And("I set {string} Guid as Id for post user Notification")
    public void iSetGuidAsIdForPostUserNotification(String value) {
        if (value.equalsIgnoreCase("valid"))
            notificationServiceReqSpecification.setId(userId);
    }

    @And("I Validate {string} value as {string} in response for Get User Notifications")
    public void iValidateValueAsInResponseForGetUserNotifications(String key, String value) {
        if (key.equalsIgnoreCase("status"))
            Assert.assertTrue(response.jsonPath().get("content[0].status").toString().equalsIgnoreCase(value));
        else if (key.equalsIgnoreCase("isDeleted"))
            Assert.assertTrue(response.jsonPath().get("content[0].isDeleted").toString().equalsIgnoreCase(value));
        else if (key.equalsIgnoreCase("sourceDomain"))
            Assert.assertTrue(response.jsonPath().get("content[0].notification.sourceDomain").toString().equalsIgnoreCase(value));
    }

    @And("I send PATCH Request to {string}")
    public void iSendPATCHRequestTo(String apiEndPoint) throws IOException {
        String endPoint = null;
        if (apiEndPoint.contains("{id}")) {
            endPoint = apiEndPoint.replace("{id}", userId);
        } else {
            endPoint = apiEndPoint;
        }
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = baseUtilities.getBaseUrl("baseUrl");
        httpRequestImpl = new PatchRequestImpl();
        response = httpRequestImpl.setPayload(payload).setUrl(endPoint).request();
        String jsonString = response.asString();
        System.out.println("PATCH Response:-" + jsonString);

    }

    @And("I set {string} user notification id for user notification")
    public void iSetUserNotificationIdForUserNotification(String value) {
        if (value.equalsIgnoreCase("valid"))
            userId = response.jsonPath().get("content[0].id");
        else if (value.equalsIgnoreCase(("valid existing")))
            userId = baseUtilities.getValidExistingUserNotificationId("existing_notification_id");
    }

    @And("I set {string} value as {string}")
    public void iSetBelowValuesFrom(String expectedField, String expectedValue) {
        if (expectedField.equalsIgnoreCase("isDeleted"))
            userNotificationReqSpecification.setIsDeleted(Boolean.valueOf(expectedValue));
        else if (expectedField.equalsIgnoreCase("status"))
            userNotificationReqSpecification.setStatus(expectedValue);

    }

    @And("I Validate expected {string} disappear in response for User Notifications")
    public void iValidateExpectedDisappearInResponseForUserNotifications(String value) {
        if (value.equalsIgnoreCase("user notification Id")) {
            List<String> actualUserIds = response.jsonPath().get("content.id");
            String expectedUserId = expectedUserNotificationId;
            boolean allMatch = actualUserIds.stream().allMatch(actualUserId -> actualUserId.equals(expectedUserId));
            assertThat(allMatch, is(false));
        } else if (value.equalsIgnoreCase("existing user notification Id")) {
            List<String> actualUserIds = response.jsonPath().get("content.id");
            String expectedUserId = baseUtilities.getValidExistingUserNotificationId("existing_notification_id");
            ;
            boolean allMatch = !actualUserIds.isEmpty() && actualUserIds.stream().allMatch(actualUserId -> actualUserId.equals(expectedUserId));
            assertThat(allMatch, is(false));
        }
    }

    @And("I Validate {string} value as {string} in response")
    public void iValidateValueAsInResponseForUpdateUserNotifications(String key, String value) {
        Assert.assertTrue(response.jsonPath().get(key).toString().equalsIgnoreCase(value));
    }

    @And("I send DELETE Request to {string}")
    public void iSendDELETERequestTo(String apiEndPoint) {
        String endPoint = null;
        if (apiEndPoint.contains("{id}")) {
            endPoint = apiEndPoint.replace("{id}", userId);
        } else {
            endPoint = apiEndPoint;
        }
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = baseUtilities.getBaseUrl("baseUrl");
        httpRequestImpl = new DeleteRequestImpl();
        response = httpRequestImpl.setUrl(endPoint).request();
        String jsonString = response.asString();
        System.out.println("DELETE Response:-" + jsonString);
    }

    @Given("I set {string} from the user profile response")
    public void iSetFromTheUserProfileResponse(String user_Id) {
        if (user_Id.equalsIgnoreCase("userId")) {
            userId = createUserProfileResponse.jsonPath().get("userId");
        } else if (user_Id.equalsIgnoreCase("existing userId")) {
            userId = baseUtilities.getValidExistingUserId("existing_userId");
        }

    }
}

