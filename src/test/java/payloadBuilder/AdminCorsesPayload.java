package payloadBuilder;

import org.json.simple.JSONObject;

public class AdminCorsesPayload {

    public static JSONObject loginAdminUserPayload(String email, String password) {

        JSONObject loginUser = new JSONObject();
        loginUser.put("email", email);
        loginUser.put("password", password);

        return loginUser;
    }

    public static JSONObject PostCoursesPayload(String title, String description, String content, String thumbnailUrl,
                                                 String duration, String level, String category, String price,
                                                 boolean isPublished, boolean isFeatured) {
        JSONObject postCourses = new JSONObject();
        postCourses.put("title", title);
        postCourses.put("description", description);
        postCourses.put("content", content);
        postCourses.put("thumbnailUrl", thumbnailUrl);
        postCourses.put("duration", duration);
        postCourses.put("level", level);
        postCourses.put("category", category);
        postCourses.put("price", price);
        postCourses.put("isPublished", isPublished);
        postCourses.put("isFeatured", isFeatured);

        return postCourses;

    }
}
