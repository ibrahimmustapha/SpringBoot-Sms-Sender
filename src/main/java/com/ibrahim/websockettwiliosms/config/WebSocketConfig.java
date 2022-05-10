package com.ibrahim.websockettwiliosms.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // used to enable our websocket server
/*
 WebSocketMessageBrokerConfigurer interface provides implementation
 to configure the websocket connection
 */
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    // configuring a message broker that will be used to route messages from one client to the other
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*
         message broker broadcasts messages to all the connected
         clients who are subscribed to the topic
         */
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    /*
     Register a websocket endpoint that clients use to connect
     to our websocket server
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         STOMP stands for Simple Text Oriented Messaging Protocol which is
         a messaging protocol that defines the format and rules for data exchange
         */
        /*
          SockJS is used to enable fallback options for browsers
          that don't support websocket
         */
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }
}
