import io.restassured.RestAssured;
import org.junit.Before;

public class Hooks {

    @Before
    public void setUp(){

        RestAssured.baseURI="https://ca-npr-eus-svc-qas.greenpond-adb7eec2.eastus.azurecontainerapps.io/";

    }
}
