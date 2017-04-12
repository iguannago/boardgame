package uk.co.boardgame.functional;

import org.junit.Test;

import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class PlayerFunctionalSpec extends BaseFunctionalSpec {
    @Test
    public void firstPlayerInTheGameShouldGetSymbolX() throws Exception {
        String newGameResource = post(url("/game"))
                .then()
                .statusCode(201).extract().header("Location");

        post(url(newGameResource + "/player"))
                .then()
                .statusCode(201)
                .header("Location", equalTo(newGameResource + "/player/x"));
    }

    @Test
    public void secondPlayerInTheGameShouldGetSymbolO() throws Exception {
        String newGameResource = post(url("/game"))
                .then()
                .statusCode(201).extract().header("Location");

        post(url(newGameResource + "/player"))
                .then()
                .statusCode(201);


        post(url(newGameResource + "/player"))
                .then()
                .statusCode(201)
                .header("Location", equalTo(newGameResource + "/player/o"));

    }

    @Test
    public void shouldNotBeAbleToAddMoreThan2PlayersToTheGame() throws Exception {
        String newGameResource = post(url("/game"))
                .then()
                .statusCode(201).extract().header("Location");

        post(url(newGameResource + "/player"))
                .then()
                .statusCode(201);

        post(url(newGameResource + "/player"))
                .then()
                .statusCode(201);

        post(url(newGameResource + "/player"))
                .then()
                .statusCode(403)
                .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
                .body("errors.code", hasItem("TOO_MANY_PLAYERS"));

    }

    @Test
    public void shouldFailWhenTryingToAddPlayerToNonExistentGame() throws Exception {
        post(url("/game/unknown/player"))
                .then()
                .statusCode(404)
                .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
                .body("errors.code", hasItem("GAME_NOT_FOUND"));
    }
}
