package lib;

import cucumber.api.java.en.When;
import environment.ConfVariables;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostSteps {

    final String ACTION = "posts/";
    JsonPath updatePostResult;
    JsonPath deletePostResult;
    JsonPath createPostResult;

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
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION)
                .statusCode();
        return statusCode;
    }

    public void createNewPost(){

        String content = Random.randomString(15);
        String title = "Title "+ Random.randomString(7);
        String excerpt = Random.randomString(8);

        JSONObject requestParams = new JSONObject();

        requestParams.put("status", "private");
        requestParams.put("password", Random.randomString(10));
        requestParams.put("comment_status", "open");

        createPostResult = SerenityRest.given()
                .auth()
                .preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .queryParam("content", content)
                .queryParam("title", title)
                .queryParam("excerpt", excerpt)
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION)
                .then()
                .extract()
                .body().jsonPath();

    }


    public void getSpecificPost(String postId){

        SerenityRest.given()
                .auth()
                .preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + postId);



    }
}
