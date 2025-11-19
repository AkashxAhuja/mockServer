package com.example.mockserver.service;

import com.example.mockserver.config.ResponseSource;
import com.example.mockserver.model.MockResponseEntity;
import com.example.mockserver.repository.MockResponseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DatabaseMockResponseProvider implements MockResponseProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseMockResponseProvider.class);

    private final MockResponseRepository repository;
    private final ObjectMapper objectMapper;

    public DatabaseMockResponseProvider(MockResponseRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseSource supportedSource() {
        return ResponseSource.DATABASE;
    }

    @Override
    public Optional<ObjectNode> getResponse(String endpoint) {
        return repository.findByEndpoint(endpoint).flatMap(this::deserializePayload);
    }

    private Optional<ObjectNode> deserializePayload(MockResponseEntity entity) {
        try {
            return Optional.of((ObjectNode) objectMapper.readTree(entity.getPayload()));
        } catch (IOException e) {
            LOGGER.warn("Unable to parse payload for endpoint {}", entity.getEndpoint(), e);
            return Optional.empty();
        }
    }
}
