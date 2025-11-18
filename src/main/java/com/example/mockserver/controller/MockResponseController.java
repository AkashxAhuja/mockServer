package com.example.mockserver.controller;

import com.example.mockserver.service.MockResponseService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MockResponseController {

    private final MockResponseService responseService;

    public MockResponseController(MockResponseService responseService) {
        this.responseService = responseService;
    }

    @GetMapping("/inq_handshake")
    public ResponseEntity<ObjectNode> handshake() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/inq_handshake"));
    }

    @GetMapping("/sub_verify_otp")
    public ResponseEntity<ObjectNode> verifyOtp() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_verify_otp"));
    }

    @GetMapping("/sub_salary_details")
    public ResponseEntity<ObjectNode> salaryDetails() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_salary_details"));
    }

    @GetMapping("/sub_eida_details")
    public ResponseEntity<ObjectNode> eidaDetails() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_eida_details"));
    }

    @GetMapping("/sub_passport_details")
    public ResponseEntity<ObjectNode> passportDetails() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_passport_details"));
    }

    @GetMapping("/sub_address_details")
    public ResponseEntity<ObjectNode> addressDetails() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_address_details"));
    }

    @GetMapping("/sub_employment_details")
    public ResponseEntity<ObjectNode> employmentDetails() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_employment_details"));
    }

    @GetMapping("/sub_fatca_crs_details")
    public ResponseEntity<ObjectNode> fatcaCrsDetails() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_fatca_crs_details"));
    }

    @GetMapping("/sub_delivery_pref")
    public ResponseEntity<ObjectNode> deliveryPreference() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_delivery_pref"));
    }

    @GetMapping("/sub_product_details")
    public ResponseEntity<ObjectNode> productDetails() {
        return ResponseEntity.ok(responseService.getResponse("/api/v1/sub_product_details"));
    }
}
