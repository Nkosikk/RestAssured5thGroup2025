package requestBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static common.BaseURI.weatherBaseURL;
import static payloadBuilder.WeatherStationAPIPayload.registerWeatherStationPayload;
import static payloadBuilder.WeatherStationAPIPayload.updateWeatherStationPayload;
import java.util.UUID;

public class WeatherStationRequestBuilder {

    private static final String API_KEY = "e339dc677155b57281f7fc3ceb12c408";

    public static Response createWeatherStationPayload() {

        String externalId = "station-" + UUID.randomUUID();
        String name = "CapeTown-" + System.currentTimeMillis();

        return RestAssured
                .given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations")
                .queryParam("appid", API_KEY)
                .contentType(ContentType.JSON)
                .body(registerWeatherStationPayload(
                        externalId,
                        name,
                        -33,
                        18,
                        15
                ))
                .when()
                .post()
                .then()
                .extract()
                .response();
    }

    public static Response retrieveWeatherStationInformation(String stationId) {

        return RestAssured
                .given()
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

    public static Response updateWeatherStationInformation(
            String stationId,
            String externalId
    ) {

        return RestAssured
                .given()
                .baseUri(weatherBaseURL)
                .basePath("/data/3.0/stations/{stationId}")
                .pathParam("stationId", stationId)
                .queryParam("appid", API_KEY)
                .contentType(ContentType.JSON)
                .body(updateWeatherStationPayload(
                        externalId,
                        "Updated-CapeTown-Station"
                ))
                .log().all()
                .when()
                .put()
                .then()
                .extract()
                .response();
    }

    public static Response deleteWeatherStation(String stationId) {

        return RestAssured
                .given()
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


}



