package wordpressapi.lib;

import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import wordpressapi.basicauth.BasicAuth;
import wordpressapi.environment.ConfVariables;

public class UserSteps {

    static final String ACTION = "users/";
    JsonPath updateUserResult;
    JsonPath deleteUserResult;
    JsonPath createUserResult;

    @Step
    public int currentStatus(){

        BasicAuth basicAuth = new BasicAuth();
        return basicAuth.basicConnection()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + ConfVariables.getUserId())
                .statusCode();
    }

    @Step
    public void getSpecificUser(String userId){

        BasicAuth basicAuth = new BasicAuth();

        basicAuth.basicConnection()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + userId);
    }

    @Step
    public void createNewUser(String username, String name, String firstName, String lastName, String email){

        BasicAuth basicAuth = new BasicAuth();

        username = username + Random.randomString(5);
        name = name + Random.randomString(4);
        firstName = firstName + Random.randomString(4);
        lastName = lastName + Random.randomString(5);
        email = email + "@" + Random.randomString(7) + ".es";

        JSONObject requestParams = new JSONObject();

        requestParams.put("username", username);
        requestParams.put("name", name);
        requestParams.put("firstName", firstName);
        requestParams.put("lastName", lastName);
        requestParams.put("email", email);
        requestParams.put("password", Random.randomString(5));

        createUserResult = basicAuth.basicConnection()
                .when()
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION)
                .then()
                .extract()
                .body().jsonPath();
    }

    @Step
    public void updateUserData(String userId){

        BasicAuth basicAuth = new BasicAuth();

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", Random.randomString(10));
        requestParams.put("firstName", Random.randomString(10));
        requestParams.put("lastName", Random.randomString(12));
        requestParams.put("password", Random.randomString(10));

        updateUserResult = basicAuth.basicConnection()
                .when()
                .body(requestParams.toJSONString())
                .put(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION+ userId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath();
    }

    @Step
    public int getUserId(){
        return updateUserResult.getInt("id");
    }

    @Step
    public void deleteSpecificUser(){

        BasicAuth basicAuth = new BasicAuth();

        deleteUserResult = basicAuth.basicConnection()
                .when()
                .queryParam("force", 1)
                .queryParam("reassign", 1)
                .delete(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION+ createUserResult.getString("id"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath();
    }
}
