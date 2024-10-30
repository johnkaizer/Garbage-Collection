package com.project.garbagecollectionsys.sms;

import com.project.garbagecollectionsys.users.User;
import com.project.garbagecollectionsys.enums.Role;
import com.project.garbagecollectionsys.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    private final CustomSmsService smsService;
    private final UserService userService;

    @Autowired
    public SmsController(CustomSmsService smsService, UserService userService) {
        this.smsService = smsService;
        this.userService = userService;
    }

    // Send SMS to all users
    @PostMapping("/send")
    public void sendSmsToAll(@RequestBody SmsRequest request) {
        List<String> allPhoneNumbers = userService.getAllUsers().stream()
                .map(User::getPhone)
                .toList();
        smsService.sendSms(request.getMessage(), allPhoneNumbers.toArray(new String[0]));
    }

    // Send SMS to users by role
    @PostMapping("/send/role")
    public void sendSmsByRole(@RequestParam Role role, @RequestBody SmsRequest request) {
        List<String> rolePhoneNumbers = userService.getUsersByRole(role).stream()
                .map(User::getPhone)
                .toList();
        smsService.sendSms(request.getMessage(), rolePhoneNumbers.toArray(new String[0]));
    }

    // Send SMS to an individual user
    @PostMapping("/send/individual")
    public void sendSmsToIndividual(@RequestParam String phone, @RequestBody SmsRequest request) {
        smsService.sendSms(request.getMessage(), phone);
    }
}
