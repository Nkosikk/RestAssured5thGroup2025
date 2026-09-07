package tests;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import payloadBuilder.WeatherStationAPIPayload;
import requestBuilder.WeatherStationRequestBuilder;

import static org.hamcrest.Matchers.*;

public class KeishiaAPITests {

    // Store ID so other tests can use it
    static String stationId;
    public static String externalId;

    @Test(priority = 1)
    public void createWeatherStation() {

        Response response = WeatherStationRequestBuilder.createWeatherStationPayload();

        response
                .then()
                .log().all()
                .statusCode(201)
                .body("ID", notNullValue());

        // Capture generated station ID
        stationId = response.jsonPath().getString("ID");
        externalId = response.jsonPath().getString("external_id");

    }

    @Test(priority = 2)
    public void getWeatherStation() {

        Response response =
                WeatherStationRequestBuilder.retrieveWeatherStationInformation(
                        KeishiaAPITests.stationId);

        response.then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 3)
    public void updateWeatherStation() {
        WeatherStationRequestBuilder.updateWeatherStation(stationId, "Updated Weather Station")
                .then()
                .statusCode(200);
    }

    @Test(priority = 4)
    public void deleteWeatherStation() {
        WeatherStationRequestBuilder.deleteWeatherStation(KeishiaAPITests.stationId)
                .then()
                .assertThat()
                .statusCode(204); // OpenWeather returns 204 No Content on successful delete
    }

    @Test(priority = 5)
    public void verifyWeatherStationDeleted() {
        // Attempt to retrieve the deleted station
        WeatherStationRequestBuilder.retrieveWeatherStationInformation(KeishiaAPITests.stationId)
                .then()
                .log().all()
                .assertThat()
                .statusCode(404) // API should return 404 since the station was deleted
                .body("message", equalTo("Station not found")); // optional: check the error message
    }

    @Test(priority = 6)

    public void emptyStationName() {

        JSONObject payload = WeatherStationAPIPayload.emptyWeatherStationNamePayload(
                KeishiaAPITests.stationId,
                "",          // empty station name
                40.7128,
                -74.0060,
                10
        );

        WeatherStationRequestBuilder.emptyStationName(KeishiaAPITests.stationId, payload)
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .body("code", equalTo(400001))
                .body("message", equalToCompressingWhiteSpace(
                        "Bad or zero length station name"
                ));

    }

    @Test(priority = 7)
    public void invalidExternalId() {

        JSONObject payload = WeatherStationAPIPayload.invalidExternalIdPayload(
                KeishiaAPITests.stationId,
                "Test",
                40.7128,
                -74.0060,
                10
        );

        WeatherStationRequestBuilder.invalidExternalId(KeishiaAPITests.stationId, payload)
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .body("code", equalTo(400001))
                .body("message", equalToCompressingWhiteSpace(
                        "Bad external id"
                ));
    }

    @Test(priority = 8)
    public void invalidRangeValuesTest() {

        JSONObject payload = WeatherStationAPIPayload.invalidRangeValues(
                KeishiaAPITests.stationId,
                "Test",
                300,
                -74.0060,
                10
        );

        WeatherStationRequestBuilder.invalidRangeValues(KeishiaAPITests.stationId, payload)
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .body("code", equalTo(400001))
                .body("message", equalToCompressingWhiteSpace(
                        "Station latitude should be in (-90:90)"
                ));
    }
}

