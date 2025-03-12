import io.restassured.RestAssured;
import org.junit.Before;

public class Hooks {

    @Before
    public void setUp(){

        RestAssured.baseURI="https://bookstore.toolsqa.com";

    }
}
