package starter.penjaminanonline;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.remote.Response;
import starter.penjaminanonline.Utils.Constant;

import java.io.File;

public class PenjaminanAPI {

    public static String POST_LOGIN_PENJ = Constant.BASE_URL + "auth/login";
    public static String POST_PENJ_BPD_JAMBI = Constant.BASE_URL + "bankjambi/penjaminanbaru";

    @Step("Post login bank")
    public Response postLogin(File json){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
        return null;
    }

    @Step("Post login bank sulselbar pusat fail")
    public void postLoginFail(String username, String password){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }")
                .post(POST_LOGIN_PENJ);
    }

    @Step("Post penj baru token fail")
    public void postPenjBaruFail(File json){
        SerenityRest.given().headers("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9." +
                        "eyJzdWIiOiJoMmhtaXRyYSIsInNjb3BlIjpbImgyaG1pdHJhIl0sImlhdCI6MTY5NjM5MTMwOSwiaXNzIjoiamFta3JpbmRvLWNvcmUiLCJleHAiOjMzOTI4NjkwMTh9.eDj-8krWaoCHaVUhE3iAbfloDjuxyC9IFcgssGvjZnI6OcqsTdxCJiTnAwUcKhYG4fppaLCRy8miM0iefaBcDe")
                .contentType(ContentType.JSON).body(json).log().all();
    }
    @Step("Post Penjaminan Baru")
    public void postPenjBaru(File json) {
        SerenityRest.given().headers("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
                        "eyJpc3MiOiJodHRwOi8vMTcyLjI3LjEuMTE3Ojc3NzcvYXBpL3YxL2F1dGgvbG9naW4iLCJpYXQiOjE3MDY1ODM4NTYsImV4cCI6MTcwNjY3MDI1NiwibmJmIjoxNzA2NTgzODU2LCJqdGkiOiJieE4xdWZJNFVQb1U1UHhWIiwic3ViIjoiMzc1IiwicHJ2IjoiMjNiZDVjODk0OWY2MDBhZGIzOWU3MDFjNDAwODcyZGI3YTU5NzZmNyIsImNyZWF0ZWRfYnkiOiIxNzE2IiwiaWRfZGRfYmFuayI6IjQyIn0." +
                        "gTjU77vteKjs2ufM26QxffmtBv6F2ldpkvavYZ8UP-c")
                .contentType(ContentType.JSON).body(json).log().all();
    }

}
