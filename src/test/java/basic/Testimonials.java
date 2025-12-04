package basic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Testimonials {

    static String baseURL = "https://www.ndosiautomation.co.za";
    static String authToken;
    static String testimonialId;

    @Test(priority = 1)
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
        authToken = responseBody.jsonPath().getString("data.token");
        Assert.assertEquals(actualStatusCode, 200, "Status code should be 200");

    }

    @Test(priority = 2)
    public void CreateTestimonialTest() {

        String path = "/API/testimonials";
        String payload = "{\n" +
                "  \"title\": \"Great Service!\",\n" +
                "  \"content\": \"This is my testimonial content describing the excellent service I received.\",\n" +
                "  \"rating\": 5,\n" +
                "  \"isPublic\": true\n" +
                "}";

        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .body(payload)
                .log().all()
                .post().prettyPeek();

        int actualStatusCode = responseBody.getStatusCode();
        testimonialId = responseBody.jsonPath().getString("data.Id");
        System.out.println("Created Testimonial ID: " + testimonialId);
        Assert.assertEquals(actualStatusCode, 201, "Status code should be 201");

    }

    @Test(priority = 3)
    public void getTestimonialsTest(){
        String path = "/API/testimonials";

        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .get().prettyPeek();

        int actualStatusCode = responseBody.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "Status code should be 200");
    }

    @Test(priority = 4)
    public void updateTestimonialTest() {
        String path = "/API/testimonials/"+ testimonialId; // Assuming testimonial ID is 1
        String payload = "{\n" +
                "  \"title\": \"Updated Testimonial Title\",\n" +
                "  \"content\": \"This is the updated testimonial content.\",\n" +
                "  \"rating\": 4,\n" +
                "  \"isPublic\": false\n" +
                "}";

        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .body(payload)
                .log().all()
                .put().prettyPeek();

        int actualStatusCode = responseBody.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "Status code should be 200");

    }

    @Test(priority = 5)
    public void deleteTestimonialTest(){
        String path = "/API/testimonials/"+ testimonialId; // Assuming testimonial ID is 1
        Response responseBody = RestAssured.given()
                .baseUri(baseURL)
                .basePath(path)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + authToken)
                .log().all()
                .delete().prettyPeek();
        int actualStatusCode = responseBody.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200, "Status code should be 200");

    }

}
