package com.ems;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmsMybatisBackendApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(EmsMybatisBackendApplicationTests.class);

    @LocalServerPort
    int randomServerPort;

    @Test
    void contextLoads() {
        logger.info("Port " + randomServerPort);
    }

}
