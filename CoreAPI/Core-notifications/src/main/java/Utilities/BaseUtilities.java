package Utilities;

import com.fasterxml.jackson.core.JsonParser;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseUtilities {

    private Properties properties;

    public BaseUtilities() {
        properties = new Properties();
        try {
            // Load properties from the config file
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl(String baseUrl) {
        return properties.getProperty(baseUrl);
    }

    public void validateJsonArraySize(Response response, String jsonObject,String expectedJsonObjectValue) {
        List<Map<String, Object>> jsonArray = response.jsonPath().getList(jsonObject);

        // Check if the JSON array is present and has non-zero size
        if (jsonArray != null && !jsonArray.isEmpty()) {
            Assert.assertTrue("Expected size of " + jsonObject + " to be equal to "+expectedJsonObjectValue, jsonArray.size() == Integer.parseInt(expectedJsonObjectValue));
            System.out.println(jsonObject + " size: " + jsonArray.size());
        } else {
            System.out.println(jsonObject + " is not present or empty.");
        }
    }

    public Map<String,String> storeIdsInMap(Response response,String jsonObject){
        List<Map<String, Object>> jsonArray = response.jsonPath().getList(jsonObject);
        HashMap<String, String> idMap = new HashMap<>();

        if (jsonArray != null && !jsonArray.isEmpty()) {
            for(Map<String,Object> map:jsonArray){
                Integer id = (Integer) map.get("id");
                idMap.put("userId",id.toString());
            }
        }
        return idMap;
    }

    public String generateToken(String userName){

        String tokenUrl = properties.getProperty("tokenUrl");
        String token=null;


        HashMap<String, String> queryparams = new HashMap<>();
        queryparams.put("client_id", System.getenv("AZURE_RPOC_CLIENT_ID"));
        queryparams.put("client_secret", System.getenv("AZURE_RPOC_CLIENT_SECRET"));
        queryparams.put("grant_type", "password");
        queryparams.put("scope", "openid "+System.getenv("AZURE_RPOC_CLIENT_ID")+" offline_access");
        queryparams.put("response_type", "token id_token");

        queryparams.put("username", userName);
        queryparams.put("password", "Admin@123");
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given()
                .queryParams(queryparams)
                .when().log().all()
                .post(tokenUrl)
                .then()
                .extract().response();

        if(response.getStatusCode()==200){

            token = response.getBody().jsonPath().get("access_token").toString();
        }

        return token;
    }

    public String getBaseUrl_UserService(String baseUrl) {
        return properties.getProperty(baseUrl);
    }

    public String getValidExistingUserNotificationId(String id){
        return properties.getProperty(id);

    }

    public String getValidExistingUserId(String id){
        return properties.getProperty(id);

    }
}
