package basic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.JSONObject;

public class Testimonials {

    static String baseURL = "https://www.ndosiautomation.co.za";
    static String authToken;

    @BeforeClass
    public void generateToken() {

        String path = "/API/login";

        String payload = "{\n" +
                "  \"email\": \"user_419809@example.com\",\n" +
                "  \"password\": \"SecurePass123!\"\n" +
                "}";

        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .body(payload)
                .post();

        String raw = responseBody.asString();
        System.out.println("RAW LOGIN RESPONSE = " + raw);

        Assert.assertEquals(responseBody.getStatusCode(), 200);


        JSONObject json = new JSONObject(raw);
        authToken = json.getJSONObject("data").getString("token");

        System.out.println("EXTRACTED TOKEN = " + authToken);

        Assert.assertNotNull(authToken, "Token should not be null");
    }

    @Test
    public void createTestimonialTest() {

        String path = "/API/testimonials";

        String payload = "{\n" +
                "  \"title\": \"Great Service!\",\n" +
                "  \"content\": \"This is my testimonial content.\",\n" +
                "  \"rating\": 5,\n" +
                "  \"isPublic\": true\n" +
                "}";

        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .body(payload)
                .post();

        System.out.println("TESTIMONIAL RESPONSE = " + responseBody.asString());

        Assert.assertEquals(responseBody.getStatusCode(), 201);
    }
}







