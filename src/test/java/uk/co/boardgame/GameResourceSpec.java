package uk.co.boardgame;

import com.jcabi.matchers.RegexMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameResourceSpec {

    @LocalServerPort
    private int port;

    private String url(String path) {
        return String.format("http://localhost:%s%s", this.port, path);
    }

    @Test
    public void createANewGame() throws Exception {
        given().proxy(8888)
                .post(url("/game"))
                .then()
                .statusCode(201)
                .header("Location", RegexMatchers.matchesPattern("/game/.+"));
    }

}
