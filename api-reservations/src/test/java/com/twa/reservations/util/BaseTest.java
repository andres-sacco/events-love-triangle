package com.twa.reservations.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseTest {

    static DockerComposeContainer dockerComposeContainer = new DockerComposeContainer(
            new File("src/test/resources/docker/docker-compose.yml"))
                    .waitingFor("localstack", Wait.forLogMessage(".*all queues are.*\\n", 1))
                    .waitingFor("api-reservation-db",
                            Wait.forLogMessage(".*MongoDB init process complete; ready for start up.*\\n", 1))
                    .withLocalCompose(true);

    @BeforeAll
    static void setUp() {
        dockerComposeContainer.start();
    }

    @AfterAll
    static void tearDown() {
        dockerComposeContainer.stop();
    }
}
