package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lib.UserSteps;
import net.thucydides.core.annotations.Steps;

import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckUsersManagementSteps {

    @Steps
    UserSteps userSteps;


    @Given("the application is running")
    public void the_application_is_running(){
        assertThat(userSteps.currentStatus()).isEqualTo(HttpStatus.SC_OK);

    }

    @Given("a new user has been created")
    public void a_new_user_has_been_created(){
        userSteps.createNewUser("P", "A", "C", "D", "email");
    }

    @When("I want to get a specific user {string}")
    public void i_want_to_get_a_specific_user(String userId){
        userSteps.getSpecificUser(userId);

    }

    @When("I want to create a new user {string}, {string}, {string}, {string}, {string}")
    public void i_want_to_create_a_new_user(String username, String name, String first_name, String last_name, String email){
        userSteps.createNewUser(username, name, first_name, last_name, email);
    }

    @When("I want to update a specific user {string}")
    public void i_want_to_update_a_specific_user(String userId){
        userSteps.updateUserData(userId);

    }

    @When("I want to delete the new user")
    public void i_want_to_delete_the_new_user(){
        userSteps.deleteSpecificUser();

    }

    @Then("the API should return the status {string}")
    public void the_API_should_return(String expectedStatus){
        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));

    }

    @Then("the user ID modified is (.*)")
    public void the_user_id_modified_is(int userId){
        assertThat(userSteps.getUserId()).isEqualTo(userId);

    }
}
