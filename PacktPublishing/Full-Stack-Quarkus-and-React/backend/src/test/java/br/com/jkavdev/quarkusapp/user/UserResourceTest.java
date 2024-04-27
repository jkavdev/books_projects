package br.com.jkavdev.quarkusapp.user;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.number.OrderingComparison;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class UserResourceTest {

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    void list() {
        RestAssured.given()
                .when().get("/api/v1/users")
                .then()
                .statusCode(200)
                .body(
                        "$.size()", OrderingComparison.greaterThanOrEqualTo(1),
                        "[0].name", Matchers.is("admin"),
                        "[0].password", CoreMatchers.nullValue()
                );
    }

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    void create() {
        // TODO: nao ta funcionando ta dando como se tivessemos cadastrando um usuario que ja existe na base
        RestAssured.given()
                .body("{\"name\":\"test\",\"password\":\"test\",\"roles\":[\"user\"]}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/users")
                .then()
                .statusCode(201)
                .body(
                        "name", Matchers.is("test"),
                        "password", CoreMatchers.nullValue(),
                        "created", CoreMatchers.not(Matchers.emptyString())
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void createUnauthorized() {
        RestAssured.given()
                .body("{\"name\":\"Jhonatan\",\"password\":\"qualquerSenha\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/users")
                .then()
                .statusCode(403);
    }

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    void createDuplicate() {
        RestAssured.given()
                .body("{\"name\":\"user\",\"password\":\"test\",\"roles\":[\"user\"]}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/users")
                .then()
                .statusCode(409);
    }

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    void update() {
        RestAssured.defaultParser = Parser.JSON;

        final var user = RestAssured.given()
                .body("{\"name\":\"test\",\"password\":\"test\",\"roles\":[\"user\"]}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/users")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(User.class);

        user.name = "updated";

        RestAssured.given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/v1/users/" + user.id)
                .then()
                .statusCode(200)
                .body(
                        "name", Matchers.is("updated"),
                        "version", Matchers.is(user.version + 1)
                );
    }

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    void updateOptimisticLock() {
        given()
                .body("{\"name\":\"updated\",\"version\":1337}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/users/0")
                .then()
                .statusCode(409);
    }

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    void updateNotFound() {
        given()
                .body("{\"name\":\"i-dont-exist\",\"password\":\"pa33\"}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/users/1337")
                .then()
                .statusCode(404);
    }

    @Test
    @TestSecurity(user = "admin", roles = "admin")
    void delete() {
        RestAssured.defaultParser = Parser.JSON;

        final var user = RestAssured.given()
                .body("{\"name\":\"test\",\"password\":\"test\",\"roles\":[\"user\"]}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/users")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(User.class);

        RestAssured.given()
                .when()
                .delete("/api/v1/users/" + user.id)
                .then()
                .statusCode(204);
        MatcherAssert.assertThat(User.findById
                (user.id).await().indefinitely(), nullValue());
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
//    @RunOnVertxContext
    void changePassword() {
        given()
                .body("{\"currentPassword\":\"admin\",\"newPassword\":\"changed\"}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/users/self/password")
                .then()
                .statusCode(200);
        assertTrue(BcryptUtil.matches("changed",
                User.<User>findById(0L).await().indefinitely().password));
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
    void changePasswordDoesntMatch() {
        given()
                .body("{\"currentPassword\": \"wrong\", \"newPassword\": \"changed\"}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/users/self/password")
                .then()
                .statusCode(409);
    }

}