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
    static String testimonialId;

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

    @Test(priority = 1)
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
        testimonialId = responseBody.jsonPath().getString("data.Id");
        System.out.println("Created Testimonial ID" +testimonialId);
        Assert.assertEquals(responseBody.getStatusCode(), 201);
    }

    @Test(priority = 2)
    public void getTestimonialsTest() {
        String path = "API/testimonials";

        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .header("Authoization", "Bearer " + authToken)
                .log().all()
                .get().prettyPeek();

        int actualStatusCode = responseBody.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "Status code should be 200");
    }

    @Test(priority = 3)
    public void updatedTestimonialTest() {
        String path = "/API/testimonials/" + testimonialId;

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
                .log().all()
                .body(payload)
                .put().prettyPeek();

        int actualStatusCode = responseBody.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "Status code should be 200");
    }


}







