package br.com.jkavdev.quarkusapp.auth;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AuthResourceTest {

    @Test
    void loginValidCredentials() {

        RestAssured.given()
                .body("{\"name\":\"admin\",\"password\":\"admin\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/auth/login")
                .then()
                .statusCode(200)
                .body(CoreMatchers.not(Matchers.emptyString()));
    }

    @Test
    void loginInvalidCredentials() {

        RestAssured.given()
                .body("{\"name\":\"admin\",\"password\":\"wrongOne\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/auth/login")
                .then()
                .statusCode(401);

    }

}