package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lib.PostSteps;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckPostManagementSteps {

    @Steps
    PostSteps postSteps;

    @Given("the application is open and running")
    public void the_application_is_open_and_running(){
        assertThat(postSteps.currentStatus()).isEqualTo(HttpStatus.SC_OK);
    }

    @When("I want to create a new post")
    public void i_want_to_create_a_new_post(){
        postSteps.createNewPost();
    }

    @When("I want to retrieve the post with id {string}")
    public void i_want_to_retrieve_the_post_with_id (String postId){
        postSteps.getSpecificPost(postId);

    }

    @Then("the API should return the code status {string}")
    public void the_API_should_return_the_status(String expectedStatus){
        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));
    }
}
