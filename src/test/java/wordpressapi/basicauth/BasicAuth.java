package wordpressapi.basicauth;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import wordpressapi.environment.ConfVariables;

public class BasicAuth {

    public RequestSpecification basicConnection(){

        return SerenityRest.given().auth().preemptive()
                .basic(ConfVariables.getUserApi(), ConfVariables.getPassApi())
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation();
    }
}

