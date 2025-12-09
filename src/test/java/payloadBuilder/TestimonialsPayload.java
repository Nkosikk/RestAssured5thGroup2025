package payloadBuilder;

import org.json.simple.JSONObject;

public class TestimonialsPayload {

    public static JSONObject loginUserPayload(String email, String password) {

        JSONObject loginUser = new JSONObject();
        loginUser.put("email",email);
        loginUser.put("password",password);

        return loginUser;
    }
    public static JSONObject createTestimonialPayload(String title, String content, Integer rating, Boolean isPublic){
        JSONObject createTestimonial = new JSONObject();
        createTestimonial.put("title",title);
        createTestimonial.put("content",content);
        createTestimonial.put("rating",rating);
        createTestimonial.containsValue(isPublic);

        return createTestimonial;
    }

    public static JSONObject updateTestimonialPayload(String title, String content, Integer rating, Boolean isPublic){
        JSONObject createTestimonial = new JSONObject();
        createTestimonial.put("title",title);
        createTestimonial.put("content",content);
        createTestimonial.put("rating",rating);
        createTestimonial.containsValue(isPublic);

        return createTestimonial;
    }
}
