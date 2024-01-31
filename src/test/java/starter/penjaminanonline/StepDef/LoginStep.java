package starter.penjaminanonline.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import java.io.File;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.remote.Response;
import starter.penjaminanonline.PenjaminanAPI;
import starter.penjaminanonline.Utils.Constant;
import starter.penjaminanonline.Utils.PenjaminanResponse;

public class LoginStep {
    @Steps
    PenjaminanAPI penjaminanAPI;
    private String authToken;

    @Given("Login Penjaminan with valid json")
    public void loginAdminValidJson(){
        File json = new File(Constant.JSON_REQUEST+"/LoginMitra.json");
        penjaminanAPI.postLogin(json);
    }

    @When("Send request post login mitra")
    public void sendRequestLoginAdmin(){
        SerenityRest.when().post(PenjaminanAPI.POST_LOGIN_PENJ);
    }

    @Then("Response body should be: \"Success login.\" and get token")
    public void responseBodyMessage(){
        String expectedMessage = "Success login.";
        String actualMessage = SerenityRest.then().extract().path(PenjaminanResponse.MESSAGE);
        assertEquals(expectedMessage, actualMessage);
    }

    @Then("Status code response should be {int}")
    public void statusCodeResponse(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }


    @And("Validate json schema login as an mitra")
    public void validateJsonSchemaLoginMitra(){
        File jsonSchema = new File(Constant.JSON_SCHEMA+"/LoginMitraSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    //negative
    @Given("Login akun with {string} and {string}")
    public void loginBankFailed(String username, String password){
        penjaminanAPI.postLoginFail(username, password);
    }

    @And("Response body message should be as status {string} as message {string} as data {string}")
    public void responseBodyMessageError(String status, String message, String data) {

            boolean statusBoolean = Boolean.parseBoolean(status);

            SerenityRest.then()
                    .body("status", equalTo(statusBoolean))
                    .body("message", equalTo(message))
                    .body(data, equalTo(null));
        }

    @And("Status code should be {int}")
    public void statusCodeShouldBeFailed(int statusCode) {
        SerenityRest.then().statusCode(statusCode);
    }


    @And("Validate json schema login failed")
    public void validateJsonSchemaLoginAdminInvalid(){
        File jsonSchema = new File(Constant.JSON_SCHEMA+"/LoginFailedSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }
    public String getAuthToken() {
        return authToken;
    }
}
