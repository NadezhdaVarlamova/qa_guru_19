package gmail.com.varlamvanadia1996.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static gmail.com.varlamvanadia1996.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class BookstoreTests {
    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    void registrationUserWithCustomAllureListenerTest() {
        String data = "{ \"userName\": \"nadya\", " +
                "\"password\": \"Nadyaq*werty123\" }";

        given()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(data)
                .log().uri()
                .log().body()
                .when()
                .post("/Account/v1/User")
                .then()
                .log().status()
                .log().body()
                .statusCode(406)
                .body("code", is("1204"))
                .body("message", is("User exists!"));
//                .body("token.size()", greaterThan(10));
    }
}
