package com.project.garbagecollectionsys.sms;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomSmsService {

    private final SmsService smsService;

    public CustomSmsService(@Value("${africastalking.username}") String username,
                            @Value("${africastalking.apiKey}") String apiKey) {
        AfricasTalking.initialize(username, apiKey);
        this.smsService = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);
    }

    public void sendSms(String message, String... phoneNumbers) {
        try {
            smsService.send(message, phoneNumbers, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

