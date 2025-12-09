package tests;

import org.testng.annotations.Test;
import requestBuilder.NdosiRequestBuilder;

import static org.hamcrest.Matchers.equalTo;

public class NdosiAPITests {

    @Test(priority = 1)
    public void loginUserTest(){
        NdosiRequestBuilder.loginUserResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }

    @Test(priority = 2)
    public void createTestimonialTest(){
        NdosiRequestBuilder.createTestimonialResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("success",equalTo(true));
    }

    @Test(priority = 3)
    public void updateTestimonialTest(){
        NdosiRequestBuilder.updateTestimonialResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }

    @Test(priority = 4)
    public void retrieveTestimonialTest(){
        NdosiRequestBuilder.retrieveTestimonialResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }

    @Test(priority = 5)
    public void removeTestimonialTest(){
        NdosiRequestBuilder.removeTestimonialResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
}
