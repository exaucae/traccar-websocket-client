package com.exaucae.traccar.client.websocket.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;


@Data
@Validated
@ConstructorBinding
@Component
@ConfigurationProperties(prefix = "traccar")
public class TraccarServerProperties {

    private  String name;

    private  String wSname;

    private  String login;

    private  String secret;
}
