package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static common.BaseURI.baseURL;
import static payloadBuilder.TestimonialsPayload.loginUserPayload;

public class NdosiRequestBuilder {

    static String authToken;

    public static Response loginUserResponse(){

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/login")
                .contentType(ContentType.JSON)
                .log().all()
                .body(loginUserPayload("user_419809@example.com","SecurePass123!"))
                .post()
                .then()
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
        return response;

    }
}
