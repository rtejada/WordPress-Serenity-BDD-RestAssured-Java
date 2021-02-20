package wordpressapi.lib;

import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import wordpressapi.basicauth.BasicAuth;
import wordpressapi.environment.ConfVariables;

public class PostSteps {

    static final String ACTION = "posts/";
    JsonPath updatePostResult;
    String passUpdate = Random.randomString(10);
    JsonPath deletePostResult;
    JsonPath createPostResult;


    @Step
    public int currentStatus(){

        BasicAuth basicAuth = new BasicAuth();
        return basicAuth.basicConnection()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION)
                .statusCode();
    }

    @Step
    public void createNewPost(String status, String password, String commentStatus, String title){

        String content = Random.randomString(15);
        String excerpt = Random.randomString(8);

        JSONObject requestParams = new JSONObject();

        requestParams.put("status", status);
        requestParams.put("password", password + Random.randomString(7));
        requestParams.put("comment_status", commentStatus);

        BasicAuth basicAuth = new BasicAuth();

        createPostResult = basicAuth.basicConnection()
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

    @Step
    public void getSpecificPost(String postId){

        BasicAuth basicAuth = new BasicAuth();

        basicAuth.basicConnection()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + postId);

    }

    @Step
    public void updateSpecificPost(String postId){

        JSONObject requestParams = new JSONObject();

        requestParams.put("status", "publish");
        requestParams.put("password", passUpdate);
        requestParams.put("comment_status", "open");

        BasicAuth basicAuth = new BasicAuth();
        updatePostResult = basicAuth.basicConnection()
                .when()
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + postId)
                .then()
                .extract()
                .body().jsonPath();
    }

    @Step
    public void deleteSpecificPost(){

        BasicAuth basicAuth = new BasicAuth();

        deletePostResult = basicAuth.basicConnection()
                .when()
                .queryParam("force", 1)
                .delete(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + createPostResult.getString("id"))
                .then()
                .extract()
                .body().jsonPath();

    }
}
