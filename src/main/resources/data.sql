INSERT INTO mock_responses (endpoint, payload) VALUES
('/api/v1/inq_handshake', '{"returnCode":"00","errorCode":"","message":{"message_en":"Handshake completed","message_ar":"انتهى المصافحة"},"data":{"jwt_token":"mock-jwt-token","ttl":3600}}'),
('/api/v1/sub_verify_otp', '{"returnCode":"00","errorCode":"","message":{"message_en":"OTP verified","message_ar":"تم التحقق من كلمة المرور لمرة واحدة"},"data":{"otpAttempts":1,"custDedupeStatus":"CLEAR","applicationNo":"APP-12345"}}'),
('/api/v1/sub_salary_details', '{"returnCode":"00"}'),
('/api/v1/sub_eida_details', '{"returnCode":"00","errorCode":"EIDA000","message":{"message_en":"EIDA validated","message_ar":"تم التحقق من الهوية"},"data":{"custDedupeStatus":"CLEAR","efrAttempts":1}}'),
('/api/v1/sub_passport_details', '{"returnCode":"00","errorCode":"PASS000","message":{"message_en":"Passport validated","message_ar":"تم التحقق من جواز السفر"},"data":{"custDedupeStatus":"CLEAR","efrAttempts":1}}'),
('/api/v1/sub_address_details', '{"returnCode":"00","message":{"message_en":"Address captured","message_ar":"تم التقاط العنوان"}}'),
('/api/v1/sub_employment_details', '{"returnCode":"00","message":{"message_en":"Employment captured","message_ar":"تم التقاط بيانات التوظيف"}}'),
('/api/v1/sub_fatca_crs_details', '{"returnCode":"00","message":{"message_en":"FATCA/CRS captured","message_ar":"تم التقاط FATCA/CRS"}}'),
('/api/v1/sub_delivery_pref', '{"returnCode":"00","message":{"message_en":"Delivery preference set","message_ar":"تم تعيين تفضيل التسليم"}}'),
('/api/v1/sub_product_details', '{"returnCode":"00","message":{"message_en":"Product created","message_ar":"تم إنشاء المنتج"},"data":{"accountType":"CURRENT","iban":"AE123000000000000001","accountNumber":"0000001"}}');