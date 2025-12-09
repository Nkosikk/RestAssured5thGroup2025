package payloadBuilder;

import org.json.simple.JSONObject;

public class TestimonialsPayload {

    public JSONObject loginUserPayload(String email, String password) {

        JSONObject loginUser = new JSONObject();
        loginUser.put("email", email);
        loginUser.put("password", password);

        return loginUser;
    }

    public JSONObject CreateTestimonialPayload(String title, String content, Integer rating, Boolean isPublic) {
        JSONObject CreateTestimonial = new JSONObject();
        CreateTestimonial.put("title", title);
        CreateTestimonial.put("content", content);
        CreateTestimonial.put("rating", rating);
        CreateTestimonial.containsValue(isPublic);

        return CreateTestimonial;
    }

    public JSONObject UpdateTestimonialPayload(String content, Integer rating) {
        JSONObject UpdateTestimonial = new JSONObject();
        UpdateTestimonial.put("content", content);
        UpdateTestimonial.put("rating", rating);

        return UpdateTestimonial;
    }


}
