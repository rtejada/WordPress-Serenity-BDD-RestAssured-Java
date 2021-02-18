package lib;

import environment.ConfVariables;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class UserSteps {

    final String ACTION = "users/";
    JsonPath updateUserResult;
    JsonPath deleteUserResult;
    JsonPath createUserResult;

    @Step
    public int currentStatus(){

        int statusCode = given()
                .auth()
                .preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + ConfVariables.getUserId())
                .statusCode();
        return statusCode;
    }

    @Step
    public void getSpecificUser(String userId){

       SerenityRest.given()
                .auth()
                .preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + userId);

    }

    @Step
    public void createNewUser(String username, String name, String first_name, String last_name, String email){

        username = username + Random.randomString(5);
        name = name + Random.randomString(4);
        first_name = first_name + Random.randomString(4);
        last_name = last_name + Random.randomString(5);
        email = email + "@" + Random.randomString(7) + ".es";


        JSONObject requestParams = new JSONObject();

        requestParams.put("username", username);
        requestParams.put("name", name);
        requestParams.put("first_name", first_name);
        requestParams.put("last_name", last_name);
        requestParams.put("email", email);
        requestParams.put("password", Random.randomString(5));

        createUserResult =SerenityRest.given()
                .auth()
                .preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION)
                .then()
                .extract()
                .body().jsonPath();

    }

    @Step
    public void updateUserData(String userId){

        JSONObject requestParams = new JSONObject();

        requestParams.put("name", Random.randomString(10));
        requestParams.put("first_name", Random.randomString(10));
        requestParams.put("last_name", Random.randomString(12));
        requestParams.put("password", Random.randomString(10));

        updateUserResult =SerenityRest.given()
                .auth()
                .preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .put(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION+ userId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath();
        //System.out.println(updateUserResult);
    }

    @Step
    public int getUserId(){
        return updateUserResult.getInt("id");
    }

    @Step
    public void deleteSpecificUser(){

        //userId = createUserResult.getString("id");

        deleteUserResult =SerenityRest.given()
                .auth()
                .preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .queryParam("force", 1)
                .queryParam("reassign", 1)
                .delete(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION+ createUserResult.getString("id"))
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body().jsonPath();
        System.out.println(deleteUserResult);
    }



}
