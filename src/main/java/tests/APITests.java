package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PayloadBuilder;
import utils.Requests;

public class APITests {

    Response response;
    @Test
    public void loginUserTest() {
        String baseURL = "https://www.ndosiautomation.co.za";
        String apiPath = "/API/login";
        String payload = PayloadBuilder.loginUserPayload("user_419809@example.com","SecurePass123!");

        response = Requests.post(baseURL + apiPath, payload);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
