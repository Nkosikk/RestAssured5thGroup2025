import org.json.simple.JSONObject;

public class AdminCoursePayloadPractise {

public static JSONObject LoginUserPayload(String  email, String password) {

    JSONObject loginUser = new JSONObject();
    loginUser.put ("email", email);
    loginUser.put("password", password);
            return loginUser;

}



}

