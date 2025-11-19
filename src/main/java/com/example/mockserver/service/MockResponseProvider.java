package com.example.mockserver.service;

import com.example.mockserver.config.ResponseSource;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Optional;

public interface MockResponseProvider {
    ResponseSource supportedSource();

    Optional<ObjectNode> getResponse(String endpoint);
}
