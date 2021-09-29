package com.exaucae.traccar.client.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketClientConfig {

    @Bean
    public StandardWebSocketClient getWebSocketClient() {
        return new StandardWebSocketClient();
    }

}
