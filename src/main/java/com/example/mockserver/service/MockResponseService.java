package com.example.mockserver.service;

import com.example.mockserver.config.MockServerProperties;
import com.example.mockserver.config.ResponseSource;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MockResponseService {

    private final MockServerProperties properties;
    private final Map<ResponseSource, MockResponseProvider> providers;

    public MockResponseService(MockServerProperties properties, List<MockResponseProvider> providers) {
        this.properties = properties;
        this.providers = new EnumMap<>(ResponseSource.class);
        providers.forEach(provider -> this.providers.put(provider.supportedSource(), provider));
    }

    public ObjectNode getResponse(String endpoint) {
        MockResponseProvider provider = providers.get(properties.getSource());
        if (provider == null) {
            throw new IllegalStateException("No provider configured for source " + properties.getSource());
        }
        return provider.getResponse(endpoint)
                .orElseThrow(() -> new IllegalArgumentException("No response configured for endpoint " + endpoint));
    }
}
