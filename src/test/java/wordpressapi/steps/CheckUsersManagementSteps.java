package wordpressapi.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import wordpressapi.lib.UserSteps;
import net.thucydides.core.annotations.Steps;

import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckUsersManagementSteps {

    @Steps
    UserSteps userSteps;


    @Given("the application is running")
    public void theApplicationIsRunning(){
        assertThat(userSteps.currentStatus()).isEqualTo(HttpStatus.SC_OK);

    }

    @Given("a new user has been created")
    public void aNewUserHasBeenCreated(){
        userSteps.createNewUser("P", "A", "C", "D", "email");
    }

    @When("I want to get a specific user {string}")
    public void iWantToGetASpecificUser(String userId){
        userSteps.getSpecificUser(userId);

    }

    @When("I want to create a new user {string}, {string}, {string}, {string}, {string}")
    public void iWantToCreateANewUser(String username, String name, String firstName, String lastName, String email){
        userSteps.createNewUser(username, name, firstName, lastName, email);
    }

    @When("I want to update a specific user {string}")
    public void iWantToUpdateASpecificUser(String userId){
        userSteps.updateUserData(userId);
    }

    @When("I want to delete the new user")
    public void iWantToDeleteTheNewUser(){
        userSteps.deleteSpecificUser();
    }

    @Then("the API should return the status {string}")
    public void theAPIShouldReturn(String expectedStatus){
        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));
    }

    @Then("the user ID modified is (.*)")
    public void theUserIdModifiedIs(int userId){
        assertThat(userSteps.getUserId()).isEqualTo(userId);

    }
}
