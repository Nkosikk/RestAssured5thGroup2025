package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static common.BaseURI.baseURL;
import static payloadBuilder.TestimonialsPayload.*;

public class NdosiRequestBuilder {

    static String authToken;
    static String testimonialId;

    public static Response loginUserResponse(String email, String password){

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/login")
                .contentType(ContentType.JSON)
                .log().all()
                .body(loginUserPayload(email,password))
                .post()
                .then()
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/schemas/loginUser.json")));
        return response;

    }

    public static Response createTestimonialResponse(){

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/testimonials")
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .log().all()
                .body(createTestimonialPayload("Testimonial1","This is an awesome class",4,true))
                .post()
                .then()
                .extract().response();

        testimonialId = response.jsonPath().getString("data.Id");
        return response;
    }

    public static Response updateTestimonialResponse(){

        return RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/testimonials/"+testimonialId)
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .log().all()
                .body(updateTestimonialPayload("Testimonial2","This is an awesome class",4,true))
                .put()
                .then()
                .extract().response();
    }

    public static Response retrieveTestimonialResponse(){

        return RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/testimonials")
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    public static Response removeTestimonialResponse(){

        return RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/testimonials/"+testimonialId)
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .log().all()
                .delete()
                .then()
                .extract().response();
    }
}
