package environment;


import java.util.Optional;

public class ConfVariables {

    public static String getUrlBase() {
        return Optional.ofNullable(System.getenv("UrlBase"))
                .orElse((String) ApplicationProperties.getInstance().get("UrlBase"));
    }

    public static String getPath() {
        return Optional.ofNullable(System.getenv("UrlPath"))
                .orElse((String) ApplicationProperties.getInstance().get("UrlPath"));
    }

    public static String getUserApi() {
        return Optional.ofNullable(System.getenv("UserApi"))
                .orElse((String) ApplicationProperties.getInstance().get("UserApi"));
    }

    public static String getPassApi() {
        return Optional.ofNullable(System.getenv("PassApi"))
                .orElse((String) ApplicationProperties.getInstance().get("PassApi"));
    }

    public static String getUserId() {
        return Optional.ofNullable(System.getenv("UserId"))
                .orElse((String) ApplicationProperties.getInstance().get("UserId"));
    }
}