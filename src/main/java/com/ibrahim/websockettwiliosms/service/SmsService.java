package com.ibrahim.websockettwiliosms.service;

import com.ibrahim.websockettwiliosms.config.TwilioConfiguration;
import com.ibrahim.websockettwiliosms.model.Sms;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

@Service
public class SmsService {

    private final static Logger log = LoggerFactory.getLogger(SmsService.class);

    private final TwilioConfiguration twilioConfig;

    @Autowired
    public SmsService(TwilioConfiguration twilioConfig) {
        this.twilioConfig = twilioConfig;
    }


    public void send(Sms sms) {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());

        Message message = Message.creator(new PhoneNumber(sms.getReceiver()), new PhoneNumber(twilioConfig.getTrialNumber()), sms.getMessage())
                .create();
        log.info("Twilio account_sid::" + message.getSid());
    }

    public void receive(MultiValueMap<String, String> smsCallback) {

    }
}
