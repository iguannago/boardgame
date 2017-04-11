package uk.co.boardgame;

import com.jcabi.matchers.RegexMatchers;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameResourceSpec {

    @LocalServerPort
    private int port;

    private String url(String path) {
        return String.format("http://localhost:%s%s", this.port, path);
    }

    @BeforeClass
    public static void init() {
        RestAssured.proxy("localhost", 8888);
    }

    @Test
    public void createANewGame() throws Exception {
                post(url("/game"))
                .then()
                .statusCode(201)
                .header("Location", RegexMatchers.matchesPattern("/game/.+"));
    }

    @Test
    public void getAnExistingGame() throws Exception {
        String newGameResource = post(url("/game"))
                                    .then()
                                    .statusCode(201).extract().header("Location");

        get(url(newGameResource))
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("api-specification/schemas/game.json"))
                .body("outcome", equalTo("IN_PROGRESS"));
    }

    @Test
    public void getANonExistentGame() throws Exception {
        get(url("/game/unknowngame"))
                .then()
                .statusCode(404)
                .body(matchesJsonSchemaInClasspath("api-specification/schemas/error.json"))
                .body("errors.code", hasItem("GAME_NOT_FOUND"));
    }

}
