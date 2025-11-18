package com.example.mockserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mockserver")
public class MockServerProperties {

    private ResponseSource source = ResponseSource.IN_MEMORY;

    public ResponseSource getSource() {
        return source;
    }

    public void setSource(ResponseSource source) {
        this.source = source;
    }
}
