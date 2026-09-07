package tests;

import org.testng.annotations.Test;
import requestBuilder.AdminCourseRequestBuilder;


import static org.hamcrest.Matchers.equalTo;

public class AdminCourseTests {

    @Test(priority = 1)
    public void loginUserTest(){
        AdminCourseRequestBuilder.loginUserResponse("avnog@gmail.com","Testing123456")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }
    @Test(priority = 2)
    public void GetCoursesTest(){
        AdminCourseRequestBuilder.getAllCoursesResponse()
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("success",equalTo(true));
    }

    @Test(priority = 3)
    public void createCourseTest(){
        AdminCourseRequestBuilder.PostCoursesResponse(
                "Test 1234",
                "Description my Desc",
                "This is the content i want to post",
                "https://www.ndosiautomation.co.za/",
                "6 Months",
                "beginner",
                "Automation",
                "650",
                true,
                true
        )
                .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .body("success",equalTo(true));
    }
}
