package br.com.jkavdev.quarkusapp.project;

import br.com.jkavdev.quarkusapp.task.Task;
import br.com.jkavdev.quarkusapp.user.User;
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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

@QuarkusTest
class ProjectResourceTest {

    @Test
    @TestSecurity(user = "user", roles = "user")
    void get() {
        RestAssured.given()
                .when().get("/api/v1/projects")
                .then()
                .statusCode(200)
                .body(
                        "$.size()", OrderingComparison.greaterThanOrEqualTo(1),
                        "[0].name", Matchers.is("Work"),
                        "[0].created", CoreMatchers.notNullValue(),
                        "[0].version", CoreMatchers.notNullValue()
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void create() {
        RestAssured.given()
                .body("{\"name\":\"Bitcoin1\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/projects")
                .then()
                .statusCode(201)
                .body(
                        "name", Matchers.is("Bitcoin1"),
                        "created", CoreMatchers.notNullValue(),
                        "version", CoreMatchers.notNullValue()
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void createDuplicate() {
        given()
                .body("{\"name\":\"create-existent\"}")
                .contentType(ContentType.JSON)
                .post("/api/v1/projects");
        given()
                .body("{\"name\":\"create-existent\"}")
                .contentType(ContentType.JSON)
                .when().post("/api/v1/projects")
                .then()
                .statusCode(409);
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void getById() {
        RestAssured.given()
                .when().get("/api/v1/projects/0")
                .then()
                .statusCode(200)
                .body(
                        "name", Matchers.is("Work"),
                        "created", CoreMatchers.notNullValue(),
                        "version", CoreMatchers.notNullValue()
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void update() {
        RestAssured.defaultParser = Parser.JSON;

        final var user = RestAssured.given()
                .body("{\"name\":\"Bitcoin2\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/projects")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(Project.class);

        user.name = "updated";

        RestAssured.given()
                .body(user)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/v1/projects/" + user.id)
                .then()
                .statusCode(200)
                .body(
                        "name", Matchers.is("updated"),
                        "created", CoreMatchers.notNullValue(),
                        "version", Matchers.is(user.version + 1)
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void updateNotFound() {
        given()
                .body("{\"name\":\"to-update\"}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/projects/1337")
                .then()
                .statusCode(404);
    }

    @Test
    @TestSecurity(user = "admin", roles = "user")
    void updateUnauthorized() {
        given()
                .body("{\"name\":\"to-update\"}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/projects/0")
                .then()
                .statusCode(401);
    }

    // ta dando erro de :: java.lang.IllegalStateException: No current Vertx context found
    @Test
    @TestSecurity(user = "user", roles = "user")
    void delete() {
        RestAssured.defaultParser = Parser.JSON;

        final var user = RestAssured.given()
                .body("{\"name\":\"Bitcoin3\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/projects")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(Project.class);

        var dependentTask = given()
                .body("{\"title\":\"dependent-task\",\"project\":{\"id\":" + user.id + "}}").contentType(ContentType.JSON)
                .post("/api/v1/tasks").as(Task.class);

        RestAssured.given()
                .when()
                .delete("/api/v1/projects/" + user.id)
                .then()
                .statusCode(204);

        MatcherAssert.assertThat(User.findById
                (user.id).await().indefinitely(), nullValue());

        assertThat(Task.<Task>findById(dependentTask.id).await().indefinitely().project, nullValue());
    }

}