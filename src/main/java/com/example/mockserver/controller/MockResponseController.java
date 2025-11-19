package com.example.mockserver.controller;

import com.example.mockserver.service.MockResponseService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/mock")
public class MockResponseController {

    private final MockResponseService responseService;

    public MockResponseController(MockResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping
    public ResponseEntity<ObjectNode> fetchMock(@RequestParam("service") String service) {
        if (!StringUtils.hasText(service)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "service query parameter is required");
        }
        return ResponseEntity.ok(responseService.getResponse(service));
    }
}
