package wordpressapi.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wordpressapi.lib.PostSteps;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckPostManagementSteps {

    @Steps
    PostSteps postSteps;

    @Given("the application is open and running")
    public void theApplicationIsOpenAndRunning(){
        assertThat(postSteps.currentStatus()).isEqualTo(HttpStatus.SC_OK); }

    @Given("a new post has been created")
    public void aNewPostHasBeenCreated () {
        postSteps.createNewPost("publish", "pw", "closed", "");
    }

    @When("I want to create a new post {string}, {string}, {string}, {string}")
    public void iWantToCreateANewPost(String status, String password, String commentStatus, String title){
        postSteps.createNewPost(status, password, commentStatus, title);
    }

    @When("I want to retrieve the post with id {string}")
    public void iWantToRetrieveThePostWithId(String postId){
        postSteps.getSpecificPost(postId);
    }

    @When("I want to update a post with id {string}")
    public void iWantToUpdateAPostWithId(String postId){
        postSteps.updateSpecificPost(postId);
    }

    @When("I want to delete the post I created")
    public void iWantToDeleteThePostICreated(){
        postSteps.deleteSpecificPost();

    }

    @Then("the API should return the code status {string}")
    public void theAPIShouldReturnTheStatus(String expectedStatus){
        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));
    }
}
