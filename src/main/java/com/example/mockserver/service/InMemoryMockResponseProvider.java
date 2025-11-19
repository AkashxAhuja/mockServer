package com.example.mockserver.service;

import com.example.mockserver.config.ResponseSource;
import com.example.mockserver.model.MessageContent;
import com.example.mockserver.model.MockResponsePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class InMemoryMockResponseProvider implements MockResponseProvider {

    private final ObjectMapper objectMapper;
    private final Map<String, ObjectNode> responses = new LinkedHashMap<>();

    public InMemoryMockResponseProvider(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadDefaults() {
        responses.put("/api/v1/inq_handshake", toJson(new MockResponsePayload(
                "00",
                "",
                new MessageContent("Handshake completed", "انتهى المصافحة"),
                objectMapper.createObjectNode()
                        .put("jwt_token", "mock-jwt-token")
                        .put("ttl", 3600)
        )));

        responses.put("/api/v1/sub_verify_otp", toJson(new MockResponsePayload(
                "00",
                "",
                new MessageContent("OTP verified", "تم التحقق من كلمة المرور لمرة واحدة"),
                objectMapper.createObjectNode()
                        .put("otpAttempts", 1)
                        .put("custDedupeStatus", "CLEAR")
                        .put("applicationNo", "APP-12345")
        )));

        responses.put("/api/v1/sub_salary_details", toJson(new MockResponsePayload(
                "00",
                "",
                null,
                null
        )));

        responses.put("/api/v1/sub_eida_details", toJson(new MockResponsePayload(
                "00",
                "EIDA000",
                new MessageContent("EIDA validated", "تم التحقق من الهوية"),
                objectMapper.createObjectNode()
                        .put("custDedupeStatus", "CLEAR")
                        .put("efrAttempts", 1)
        )));

        responses.put("/api/v1/sub_passport_details", toJson(new MockResponsePayload(
                "00",
                "PASS000",
                new MessageContent("Passport validated", "تم التحقق من جواز السفر"),
                objectMapper.createObjectNode()
                        .put("custDedupeStatus", "CLEAR")
                        .put("efrAttempts", 1)
        )));

        responses.put("/api/v1/sub_address_details", toJson(new MockResponsePayload(
                "00",
                "",
                new MessageContent("Address captured", "تم التقاط العنوان"),
                null
        )));

        responses.put("/api/v1/sub_employment_details", toJson(new MockResponsePayload(
                "00",
                "",
                new MessageContent("Employment captured", "تم التقاط بيانات التوظيف"),
                null
        )));

        responses.put("/api/v1/sub_fatca_crs_details", toJson(new MockResponsePayload(
                "00",
                "",
                new MessageContent("FATCA/CRS captured", "تم التقاط FATCA/CRS"),
                null
        )));

        responses.put("/api/v1/sub_delivery_pref", toJson(new MockResponsePayload(
                "00",
                "",
                new MessageContent("Delivery preference set", "تم تعيين تفضيل التسليم"),
                null
        )));

        responses.put("/api/v1/sub_product_details", toJson(new MockResponsePayload(
                "00",
                "",
                new MessageContent("Product created", "تم إنشاء المنتج"),
                objectMapper.createObjectNode()
                        .put("accountType", "CURRENT")
                        .put("iban", "AE123000000000000001")
                        .put("accountNumber", "0000001")
        )));
    }

    @Override
    public ResponseSource supportedSource() {
        return ResponseSource.IN_MEMORY;
    }

    @Override
    public Optional<ObjectNode> getResponse(String endpoint) {
        return Optional.ofNullable(responses.get(endpoint));
    }

    private ObjectNode toJson(MockResponsePayload payload) {
        ObjectNode root = objectMapper.createObjectNode();
        root.put("returnCode", payload.returnCode());
        if (payload.errorCode() != null && !payload.errorCode().isEmpty()) {
            root.put("errorCode", payload.errorCode());
        }
        if (payload.message() != null) {
            ObjectNode messageNode = objectMapper.createObjectNode();
            messageNode.put("message_en", payload.message().messageEn());
            messageNode.put("message_ar", payload.message().messageAr());
            root.set("message", messageNode);
        }
        if (payload.data() != null) {
            root.set("data", payload.data());
        }
        return root;
    }
}