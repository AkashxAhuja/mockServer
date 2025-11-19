package com.example.mockserver.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

public record MockResponsePayload(String returnCode, String errorCode, MessageContent message, ObjectNode data) {
}
