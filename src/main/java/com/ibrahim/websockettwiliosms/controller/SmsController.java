package com.ibrahim.websockettwiliosms.controller;

import com.ibrahim.websockettwiliosms.model.Sms;
import com.ibrahim.websockettwiliosms.service.SmsService;
import com.twilio.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class SmsController {

    @Autowired
    SmsService smsService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private final String TOPIC_DESTINATION = "/topic/sms";

    // broadcast to all customers
    @PostMapping("/sms")
    public void smsSubmit(@RequestBody Sms sms) {
        try {
            smsService.send(sms);
        } catch (ApiException e) {
            // Used to send messages to connected clients from any part of the application
            webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending SMS" + e.getMessage());
            throw e;
        }
        webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + "SMS has been sent: " + sms.getMessage());
    }

    @PostMapping("/smscallback")
    public void smsCallback(@RequestBody MultiValueMap<String, String> map) {
        smsService.receive(map);
        webSocket.convertAndSend(TOPIC_DESTINATION, ": Twilio has made a callback request! Here are the contents: " + map.toString());
    }

    private String getTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").
                format(LocalDateTime.now());
    }
}
