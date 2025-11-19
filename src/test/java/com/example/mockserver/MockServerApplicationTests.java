package com.example.mockserver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.mockserver.config.MockServerProperties;
import com.example.mockserver.config.ResponseSource;
import com.example.mockserver.service.MockResponseService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "mockserver.source=IN_MEMORY")
class MockServerApplicationTests {

    @Autowired
    private MockResponseService responseService;

    @Autowired
    private MockServerProperties properties;

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    void fetchesMockByServiceQueryParameter() throws Exception {
        mockMvc.perform(get("/mock").queryParam("service", "/api/v1/inq_handshake"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.returnCode").value("00"))
                .andExpect(jsonPath("$.data.jwt_token").value("mock-jwt-token"));
    }
}