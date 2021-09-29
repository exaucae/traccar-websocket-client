package com.exaucae.traccar.client.websocket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RestTemplateClientConfig {
    private final TraccarServerProperties traccarProperties;

    @Bean
    public RestTemplate getRestTemplateClient() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthentication(traccarProperties.getLogin(), traccarProperties.getSecret())
                .build();

        restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        return restTemplate;
    }



}
