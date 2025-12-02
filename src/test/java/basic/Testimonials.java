package basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testimonials {

    static String baseURL = "https://www.ndosiautomation.co.za";
    @Test
    public void loginUserTest() {

        String path = "/API/login";
        String payload = "{\n" +
                "    \"email\": \"user_419809@example.com\",\n" +
                "    \"password\": \"SecurePass123!\"\n" +
                "}";

        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .body(payload)
                .log().all()
                .post().prettyPeek();

        int actualStatusCode = responseBody.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "Status code should be 200");

    }


}
