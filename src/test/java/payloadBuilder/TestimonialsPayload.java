package payloadBuilder;

import org.json.simple.JSONObject;

public class TestimonialsPayload {

    public JSONObject loginUserPayload(String email, String password) {

        JSONObject loginUser = new JSONObject();
        loginUser.put("email",email);
        loginUser.put("password",password);

        return loginUser;
    }
}
