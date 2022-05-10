package com.ibrahim.websockettwiliosms;

import org.apache.logging.log4j.message.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class WebsocketTwilioSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketTwilioSmsApplication.class, args);
	}
}
