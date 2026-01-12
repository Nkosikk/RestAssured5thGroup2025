package payloadBuilder;

import org.json.simple.JSONObject;

public class WeatherStationAPIPayload {

    public static JSONObject registerWeatherStationPayload(
            String external_id,
            String name,
            Integer latitude,
            Integer longitude,
            Integer altitude
    ) {

        JSONObject payload = new JSONObject();

        payload.put("external_id", external_id);
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
            String name
    ) {
        JSONObject payload = new JSONObject();
        payload.put("external_id", externalId);
        payload.put("name", name);
        return payload;
    }



 /*   public class WeatherStationAPIPayload {

        public static JSONObject emptyWeatherStationNamePayload(
                String externalId,
                String name
        ) {
            JSONObject payload = new JSONObject();
            payload.put("external_id", externalId);
            payload.put("name", name);
            return payload;
        }
    }
*/



}






