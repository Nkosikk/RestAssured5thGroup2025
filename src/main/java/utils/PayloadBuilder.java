package utils;

public class PayloadBuilder {

    public static String loginUserPayload(String email, String password) {
        return "{\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\"\n" +
                "}";
    }
}
