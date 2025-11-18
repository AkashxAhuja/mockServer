package com.example.mockserver;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.mockserver.config.MockServerProperties;
import com.example.mockserver.config.ResponseSource;
import com.example.mockserver.service.MockResponseService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "mockserver.source=IN_MEMORY")
class MockServerApplicationTests {

    @Autowired
    private MockResponseService responseService;

    @Autowired
    private MockServerProperties properties;

    @Test
    void contextLoads() {
        assertThat(properties.getSource()).isEqualTo(ResponseSource.IN_MEMORY);
    }

    @Test
    void returnsInMemoryHandshakeResponse() {
        ObjectNode response = responseService.getResponse("/api/v1/inq_handshake");
        assertThat(response.get("returnCode").asText()).isEqualTo("00");
        assertThat(response.get("data").get("jwt_token").asText()).isEqualTo("mock-jwt-token");
    }
}
