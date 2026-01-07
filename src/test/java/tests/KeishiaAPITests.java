package tests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import requestBuilder.WeatherStationRequestBuilder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class KeishiaAPITests {

    // Store ID so other tests can use it
    static String stationId;
    static String externalId;

    @Test(priority = 1)
    public void createWeatherStation() {

        Response response =
                WeatherStationRequestBuilder.createWeatherStationPayload();

        response
                .then()
                .log().all()
                .statusCode(201)
                .body("ID", notNullValue());

        // ✅ Capture generated station ID
        stationId = response.jsonPath().getString("ID");
        externalId = response.jsonPath().getString("external_id");

    }

    @Test(priority = 2)
    public void getWeatherStation() {

        Response response =
                WeatherStationRequestBuilder.retrieveWeatherStationInformation(
                        KeishiaAPITests.stationId
                );

        response.then()
                .statusCode(200)
                .log().all();
    }


    @Test(priority = 3)
    public void updateWeatherStationInformation() {


        String externalId = KeishiaAPITests.externalId;

        WeatherStationRequestBuilder.updateWeatherStationInformation(
                        KeishiaAPITests.stationId,
                        externalId
                )
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)  // ✅ should be 200 OK, not 400
                .body("name", equalTo("Updated-CapeTown-Station")); // ✅ verify the new name
    }

    @Test(priority = 4)
    public void deleteWeatherStation() {
        WeatherStationRequestBuilder.deleteWeatherStation(KeishiaAPITests.stationId)
                .then()
                .assertThat()
                .statusCode(204); // OpenWeather returns 204 No Content on successful delete
    }



}

