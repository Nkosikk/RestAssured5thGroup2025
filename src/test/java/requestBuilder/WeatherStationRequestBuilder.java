package requestBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static common.BaseURI.weatherBaseURL;
import static io.restassured.RestAssured.given;
import static payloadBuilder.WeatherStationAPIPayload.registerWeatherStationPayload;
import static payloadBuilder.WeatherStationAPIPayload.updateWeatherStationPayload;
import java.util.UUID;
import org.json.JSONObject;


public class WeatherStationRequestBuilder {

    private static final String API_KEY = "e339dc677155b57281f7fc3ceb12c408";

    public static Response createWeatherStationPayload() {
        String externalId =
                "station" + UUID.randomUUID().toString().replace("-", "");

        String name = "CapeTown" + System.currentTimeMillis();
        System.out.println("EXTERNAL ID SENT >>> " + externalId);


        return given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations")
                .queryParam("appid", API_KEY)
                .contentType(ContentType.JSON)
                .body(registerWeatherStationPayload(
                        externalId,
                        name,
                        -33.9249,
                        18.4241,
                        15
                ).toString())

                .log().all()
                .when()
                .post()
                .then()
                .extract()
                .response();
    }


    public static Response retrieveWeatherStationInformation(String stationId) {

        return given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations/{stationId}")
                .pathParam("stationId", stationId)
                .queryParam("appid", API_KEY)
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get()
                .then()
                .extract()
                .response();
    }

    public static Response updateWeatherStation(String stationId, String newName) {
        // Hardcoded values from your created station
        String externalId = "TEST_STATION_001";
        double latitude = 33.44;
        double longitude = -94.04;
        double altitude = 150;

        JSONObject payload = updateWeatherStationPayload(
                externalId,
                newName,
                latitude,
                longitude,
                altitude
        );

        return RestAssured.given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations")
                .pathParam("stationId", stationId)
                .queryParam("appid", API_KEY)
                .contentType(ContentType.JSON)
                .body(payload.toString())
                .log().all()
                .when()
                .put("/{stationId}");
    }




    public static Response deleteWeatherStation(String stationId) {

        return given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations/{stationId}")
                .pathParam("stationId", stationId)
                .queryParam("appid", API_KEY)
                .log().all()
                .when()
                .delete()
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response emptyStationName(String stationId, JSONObject payload) {

        return RestAssured
                .given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations/{stationId}")
                .pathParam("stationId", stationId)
                .queryParam("appid", API_KEY)
                .contentType("application/json")
                .body(payload.toString())
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public static Response invalidExternalId(String stationId, JSONObject payload) {

        return RestAssured
                .given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations/{stationId}")
                .pathParam("stationId", stationId)
                .queryParam("appid", API_KEY)
                .contentType("application/json")
                .body(payload.toString())
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public static Response invalidRangeValues(String stationId, JSONObject payload) {

        return RestAssured
                .given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations/{stationId}")
                .pathParam("stationId", stationId)
                .queryParam("appid", API_KEY)
                .contentType("application/json")
                .body(payload.toString())
                .when()
                .put()
                .then()
                .extract()
                .response();
    }


}







