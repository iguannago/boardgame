package uk.co.boardgame.functional;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Functional tests should be in a separate source directory and Run after the Unit tests but given the time constraints,
 * I have kept them together
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseFunctionalSpec {
    @LocalServerPort
    private int port;

    protected String url(String path) {
        return String.format("http://localhost:%s%s", this.port, path);
    }
}
