package requestBuilder;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;

import static common.BaseURI.baseURL;
import static payloadBuilder.AdminCorsesPayload.PostCoursesPayload;
import static payloadBuilder.AdminCorsesPayload.loginAdminUserPayload;
import static payloadBuilder.TestimonialsPayload.loginUserPayload;

public class AdminCourseRequestBuilder {
    static String authToken;
    //static String adminCourseId;

    public static Response loginUserResponse(String email, String password){

        Response response = RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/login")
                .contentType(ContentType.JSON)
                .log().all()
                .body(loginAdminUserPayload(email,password))
                .post()
                .then()
                .extract().response();

        authToken = response.jsonPath().getString("data.token");
        return response;

    }
    public static Response getAllCoursesResponse(){

        return RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/admin/courses")
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .log().all()
                .get()
                .then()
                .extract().response();
    }

    public static Response PostCoursesResponse(String title, String description, String content, String thumbnailUrl,
                                               String duration, String level, String category, String price,
                                               boolean isPublished, boolean isFeatured){

        return RestAssured.given()
                .baseUri(baseURL)
                .basePath("/API/admin/courses")
                .header("Authorization", "Bearer " + authToken)
                .contentType(ContentType.JSON)
                .log().all()
                .body(PostCoursesPayload(title,description,content,thumbnailUrl,duration,level,category,price,isPublished,isFeatured))
                .post()
                .then()
                .extract().response();
    }
}
