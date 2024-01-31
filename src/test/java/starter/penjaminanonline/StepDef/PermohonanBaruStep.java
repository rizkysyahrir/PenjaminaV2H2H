package starter.penjaminanonline.StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import starter.penjaminanonline.PenjaminanAPI;
import starter.penjaminanonline.Utils.Constant;
import starter.penjaminanonline.Utils.JsonRequest;

import java.io.File;

import static org.hamcrest.core.IsEqual.equalTo;

public class PermohonanBaruStep {
    @Steps
    PenjaminanAPI penjaminanAPI;
    private String authToken;
    private String jsonString;

    //negative case

    @Given("Post penjaminan baru")
    public void postPenjBaruWOauthor(){
        File json = new File(Constant.JSON_REQUEST+"/PenjBaru.json");
        penjaminanAPI.postPenjBaru(json);
        SerenityRest.given().body(json).when().post(PenjaminanAPI.POST_LOGIN_PENJ);
        // Ekstrak token dari respons dan simpan dalam variabel authToken
        authToken = SerenityRest.then().extract().path("token");
    }

    @When("Send request Penjaminan Baru")
    public void sendRequestPenjBaru(){
        jsonString = JsonRequest.generateJsonRequest();
        // Menampilkan JSON request (opsional, untuk keperluan debugging)
        System.out.println("JSON Request: " + jsonString);
        // Menggunakan jsonString untuk mengirim request
        SerenityRest.given().headers("Authorization","Bearer "+ getAuthToken())
                .contentType(ContentType.JSON)
                .body(jsonString)
                .when()
                .post(PenjaminanAPI.POST_PENJ_BPD_JAMBI);
    }

    @Then("Response body success and shown message {string}")
    public void responseBodyPenjBaruFail(String message){
        SerenityRest.then()
                .body("message", equalTo(message));
    }

    @And("Validate json schema post Penjaminan Baru fail")
    public void validateJsonSchemaPenjBaruFail(){
        File jsonSchema = new File(Constant.JSON_SCHEMA+"/PenjBaruSchema.json");
        SerenityRest.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    //Case Validation

    @Given("Post permohonan baru Mikro Sumut")
    public void postPermohonanBaru(){
        File json = new File(Constant.JSON_REQUEST+"/PenjBaru.json");
        penjaminanAPI.postPenjBaruFail(json);
    }
    public String getAuthToken() {
        return authToken;
    }
}
