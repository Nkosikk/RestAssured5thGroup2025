package tests;

import org.testng.annotations.Test;
import requestBuilder.NdosiRequestBuilder;

import static org.hamcrest.Matchers.equalTo;

public class NdosiAPITests {

    @Test
    public void loginUserTest(){
        NdosiRequestBuilder.loginUserResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
}
