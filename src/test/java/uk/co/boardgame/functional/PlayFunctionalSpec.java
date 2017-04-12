package uk.co.boardgame.functional;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class PlayFunctionalSpec extends BaseFunctionalSpec {

    private String location(Object row, Object col) {
        return String.format("{\"row\":%s, \"column\":%s}", row, col);
    }

    @Test
    public void playerShouldBeAbleToPlaceHisSymbolOnAnEmptyLocationOnBoard() {
        String newGame = post(url("/game")).then().statusCode(201).extract().header("Location");

        post(url(newGame + "/player")).then().statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 1))
                .put(url(newGame + "/player/x"))
                .then().statusCode(200)
                .body("outcome", equalTo("IN_PROGRESS"));
    }

    @Test
    public void playerMoveShouldFailIfRowOrColumnIsNotANumber() {
        String newGame = post(url("/game")).then().statusCode(201).extract().header("Location");

        post(url(newGame + "/player")).then().statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(location("a", "b"))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(400);
    }

    @Test
    public void playerMoveShouldFailIfRowOrColumnIsANumberGreaterThan3() {
        String newGame = post(url("/game")).then().statusCode(201).extract().header("Location");

        post(url(newGame + "/player")).then().statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(location(4, 4))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(400)
            .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
            .body("errors.find { it.path == '/row' }.code", equalTo("INVALID_VALUE"))
            .body("errors.find { it.path == '/column' }.code", equalTo("INVALID_VALUE"));
    }

    @Test
    public void playerMoveShouldFailIfGameIsNonExistent() {
        given()
            .contentType(ContentType.JSON)
            .body(location(1, 1))
            .put(url("/game/unknown/player/x"))
            .then()
            .statusCode(404)
            .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
            .body("errors.code", hasItem("GAME_NOT_FOUND"));
    }

    @Test
    public void playerShouldNotBeAbleToPlaceHisSymbolOnOccupiedLocationOnBoard() {
        String newGame = post(url("/game")).then().statusCode(201).extract().header("Location");

        post(url(newGame + "/player")).then().statusCode(201);
        post(url(newGame + "/player")).then().statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 1))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 1))
            .put(url(newGame + "/player/o"))
            .then()
            .statusCode(409)
            .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
            .body("errors.code", hasItem("LOCATION_OCCUPIED"));

    }

    @Test
    public void playerShouldNotBeAbleToMake2ConsecutiveMoves() {
        String newGame = post(url("/game")).then().statusCode(201).extract().header("Location");

        post(url(newGame + "/player")).then().statusCode(201);
        post(url(newGame + "/player")).then().statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 1))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 2))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(403)
            .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
            .body("errors.code", hasItem("INVALID_MOVE"));
    }

    @Test
    public void playerOCannotMakeTheFirstMove() {
        String newGame = post(url("/game")).then().statusCode(201).extract().header("Location");

        post(url(newGame + "/player")).then().statusCode(201);
        post(url(newGame + "/player")).then().statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 1))
            .put(url(newGame + "/player/o"))
            .then()
            .statusCode(403)
            .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
            .body("errors.code", hasItem("INVALID_FIRST_MOVE"));
    }

    @Test
    public void playerShouldWinIfTheyGetThreeOfTheirMarksInAHorizontalRow() {
        String newGame = post(url("/game")).then().statusCode(201).extract().header("Location");

        post(url(newGame + "/player")).then().statusCode(201);
        post(url(newGame + "/player")).then().statusCode(201);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 1))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body(location(2, 1))
            .put(url(newGame + "/player/o"))
            .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 2))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body(location(2, 2))
            .put(url(newGame + "/player/o"))
            .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body(location(1, 3))
            .put(url(newGame + "/player/x"))
            .then()
            .statusCode(200)
            .body("outcome", equalTo("X_WON"));

        given()
            .contentType(ContentType.JSON)
            .body(location(2, 3))
            .put(url(newGame + "/player/o"))
            .then()
            .statusCode(403)
            .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
            .body("errors.code", hasItem("GAME_OVER"));


    }

}
