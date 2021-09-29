package com.exaucae.traccar.client.websocket;

import com.exaucae.traccar.client.websocket.config.TraccarServerProperties;
import com.exaucae.traccar.client.websocket.util.CookieParser;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Component
public class SessionCookieManager implements ISessionCookieCapable {

    private final RestTemplate restTemplate;
    private final TraccarServerProperties traccarProperties;


    public LoadingCache<String, String> sessionCookieCache =
            CacheBuilder.newBuilder()
                    .maximumSize(100)
                    .expireAfterAccess(24, TimeUnit.HOURS)
                    .build(new CacheLoader<>() {

                        @Override
                        public String load(@Nullable String cookie) {
                            return getSessionCookie();
                        }
                    });

    public SessionCookieManager(RestTemplate restTemplate, TraccarServerProperties traccarProperties) {
        this.restTemplate = restTemplate;
        this.traccarProperties = traccarProperties;
    }


    public LoadingCache<String, String> getSessionCookieCache() {
        return sessionCookieCache;
    }


    @Override
    public String getSessionCookie() {

        try {

            ResponseEntity<Object> res = restTemplate.getForEntity(traccarProperties.getName().concat("/api/session"), Object.class);
            if (res.getStatusCode().is2xxSuccessful()) {
                String set_cookie = res.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
                return CookieParser.getValueOf("JSESSIONID", set_cookie);
            }
        } catch (Exception e) {


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
            data.add("email", traccarProperties.getLogin());
            data.add("password", traccarProperties.getSecret());
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

            ResponseEntity<Object> response = restTemplate.postForEntity(traccarProperties.getName().concat("/api/session"), request, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                String set_cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
                return CookieParser.getValueOf("JSESSIONID", set_cookie);
            }
        }

        return null;
    }
}
