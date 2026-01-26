package payloadBuilder;
import org.json.JSONObject;

public class WeatherStationAPIPayload {

    public static JSONObject registerWeatherStationPayload(
            String externalId,
            String name,
            double latitude,
            double longitude,
            int altitude
    ) {
        JSONObject payload = new JSONObject();

        payload.put("external_id", externalId);
        payload.put("name", name);
        payload.put("latitude", latitude);
        payload.put("longitude", longitude);
        payload.put("altitude", altitude);
        payload.put("type", "m");
        payload.put("date", 1480508280);

        return payload;
    }

    public static JSONObject updateWeatherStationPayload(
            String externalId,
            String name,
            double latitude,
            double longitude,
            double altitude) {

        JSONObject payload = new JSONObject();
        payload.put("external_id", externalId);
        payload.put("name", name);
        payload.put("latitude", latitude);
        payload.put("longitude", longitude);
        payload.put("altitude", altitude);

        return payload;
    }

    public static JSONObject emptyWeatherStationNamePayload(
            String externalId,
            String name,
            double latitude,
            double longitude,
            int altitude
    ) {
        JSONObject payload = new JSONObject();

        payload.put("external_id", externalId);
        //payload.put("name",name);
        payload.put("latitude", latitude);
        payload.put("longitude", longitude);
        payload.put("altitude", altitude);
        payload.put("type", "m");
        payload.put("date", 1480508280);

        return payload;
    }

    public static JSONObject invalidExternalIdPayload(
            String externalId,
            String name,
            double latitude,
            double longitude,
            int altitude
    ) {
        JSONObject payload = new JSONObject();

        //payload.put("external_id", externalId);
        payload.put("name",name);
        payload.put("latitude", latitude);
        payload.put("longitude", longitude);
        payload.put("altitude", altitude);
        payload.put("type", "m");
        payload.put("date", 1480508280);

        return payload;
    }


    public static JSONObject invalidRangeValues(
            String externalId,
            String name,
            double latitude,
            double longitude,
            int altitude
    ) {
        JSONObject payload = new JSONObject();

        payload.put("external_id", externalId);
        payload.put("name",name);
        payload.put("latitude", latitude);
        payload.put("longitude", longitude);
        payload.put("altitude", altitude);
        payload.put("type", "m");
        payload.put("date", 1480508280);

        return payload;
    }




}










