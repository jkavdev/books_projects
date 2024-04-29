package br.com.jkavdev.quarkusapp.task;

import br.com.jkavdev.quarkusapp.user.User;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class TaskResourceTest {

    @Test
    @TestSecurity(user = "user", roles = "user")
    void get() {
        RestAssured.given()
                .body("{\"title\":\"to-be-listed\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/tasks");

        RestAssured.given()
                .when().get("/api/v1/tasks")
                .then()
                .statusCode(200)
                .body("$",
                        allOf(
                                hasItem(
                                        hasEntry("title", "to-be-listed")
                                ),
                                everyItem(
                                        hasEntry(is("user"), (Matcher) hasEntry("name", "user"))
                                )
                        )
                );
//                .body(
//                        "$.size()", OrderingComparison.greaterThanOrEqualTo(1),
//                        "[0].title", Matchers.is("task-create"),
//                        "[0].description", CoreMatchers.nullValue(),
//                        "[0].priority", CoreMatchers.nullValue(),
//                        "[0].created", CoreMatchers.notNullValue(),
//                        "[0].version", CoreMatchers.notNullValue()
//                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void create() {
        RestAssured.given()
                .body("{\"title\":\"task-create\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/tasks")
                .then()
                .statusCode(201)
                .body(
                        "title", Matchers.is("task-create"),
                        "description", CoreMatchers.nullValue(),
                        "priority", CoreMatchers.nullValue(),
                        "created", CoreMatchers.notNullValue(),
                        "version", CoreMatchers.notNullValue()
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void getById() {
        final var task = RestAssured.given()
                .body("{\"title\":\"to-be-listed\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/tasks")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(Task.class);

        RestAssured.given()
                .when()
                .get("/api/v1/tasks/" + task.id)
                .then()
                .statusCode(200)
                .body(
                        "title", Matchers.is("to-be-listed"),
                        "description", CoreMatchers.nullValue(),
                        "priority", CoreMatchers.nullValue(),
                        "created", CoreMatchers.notNullValue(),
                        "version", CoreMatchers.notNullValue()
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void update() {
        RestAssured.defaultParser = Parser.JSON;

        final var task = RestAssured.given()
                .body("{\"title\":\"to-be-listed\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/tasks")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(Task.class);

        task.title = "updated";

        RestAssured.given()
                .body(task)
                .contentType(ContentType.JSON)
                .when()
                .put("/api/v1/tasks/" + task.id)
                .then()
                .statusCode(200)
                .body(
                        "title", Matchers.is("updated"),
                        "description", CoreMatchers.nullValue(),
                        "priority", CoreMatchers.nullValue(),
                        "created", CoreMatchers.notNullValue(),
                        "version", Matchers.is(task.version + 1)
                );
    }

    @Test
    @TestSecurity(user = "user", roles = "user")
    void updateNotFound() {
        given()
                .body("{\"title\":\"updated\"}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/tasks/1337")
                .then()
                .statusCode(404);
    }

    // ta dando erro de :: java.lang.IllegalStateException: No current Vertx context found
    @Test
    @TestSecurity(user = "user", roles = "user")
    void updateForbidden() {
        final User admin = User.<User>findById(0L).await().indefinitely();
        Task adminTask = new Task();
        adminTask.title = "admins-task";
        adminTask.user = admin;
        adminTask = adminTask.<Task>persistAndFlush().await().indefinitely();

        given()
                .body("{\"title\":\"to-update\"}")
                .contentType(ContentType.JSON)
                .when().put("/api/v1/tasks/" + adminTask.id)
                .then()
                .statusCode(401); // TODO: TaskService UnauthorizedException should be changed to ForbiddenException
    }

    // ta dando erro de :: java.lang.IllegalStateException: No current Vertx context found
    @Test
    @TestSecurity(user = "user", roles = "user")
    void complete() {
        RestAssured.defaultParser = Parser.JSON;

        final var task = RestAssured.given()
                .body("{\"title\":\"to-be-listed\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/tasks")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(Task.class);

        RestAssured.given()
                .body("\"true\"")
                .contentType(ContentType.JSON)
                .when()
                .put("/api/v1/tasks/" + task.id + "/complete")
                .then()
                .statusCode(200)
                .body(
                        Matchers.is("true")
                );

        assertThat(Task.findById(task.id).await().indefinitely(),
                allOf(
                        hasProperty("complete", notNullValue()),
                        hasProperty("version", is(task.version + 1))
                ));
    }

    // ta dando erro de :: java.lang.IllegalStateException: No current Vertx context found
    @Test
    @TestSecurity(user = "user", roles = "user")
    void delete() {
        RestAssured.defaultParser = Parser.JSON;

        final var task = RestAssured.given()
                .body("{\"title\":\"to-be-listed\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/api/v1/tasks")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(Task.class);

        RestAssured.given()
                .when()
                .delete("/api/v1/tasks/" + task.id)
                .then()
                .statusCode(204);

        MatcherAssert.assertThat(User.findById
                (task.id).await().indefinitely(), nullValue());
    }

}