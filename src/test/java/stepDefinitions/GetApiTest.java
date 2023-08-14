package stepDefinitions;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetApiTest {

    Response response;

    @Given("^the valid endpoint to fetch users$")
    public void the_valid_endpoint_to_fetch_users() {
        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="/api/users";
    }

    @When("^the request is sent to server with page number as (.*)$")
    public void the_request_is_sent_to_server_with_page_number_as(String pageNumber) {
        response = given().
                queryParam("page",pageNumber).
                when().
                get().
                then().
                contentType(ContentType.JSON).
                extract().response();
    }


    @Then("^validate the response of first user record having email as (.*)$")
    public void validate_the_response_of_first_user_record_having_email_as(String emailID) {
        String userEmail = response.path("data[0].email");
        Assert.assertEquals(userEmail, emailID);
    }

}