package com.exaucae.traccar.client.websocket;

import com.exaucae.traccar.client.websocket.config.TraccarServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;

// In case someone wonders why traccar socket connection is suddenly closed on startup when no device is online, see : https://github.com/traccar/traccar/issues/4629#issuecomment-772637486
@Component
class GetAllPositionWebSocketClientAdapter extends AbstractWebSocketClientAdapter implements IGetAllPositionPort {

    private final TraccarServerProperties traccarProperties;
    private final SessionCookieManager cookieManager;


    @Autowired
    GetAllPositionWebSocketClientAdapter(TraccarServerProperties traccarProperties, SessionCookieManager cookieManager) {
        super();
        super.setWebSocketHandler(this);
        this.traccarProperties = traccarProperties;
        this.cookieManager = cookieManager;
    }

    @Override
    public void handle() {
        start();
    }


    @Override
    protected String getEndpoint() {
        return traccarProperties.getWSname()
                .concat("/api/socket");
    }

    @Override
    protected WebSocketHttpHeaders getHeaders() {
        WebSocketHttpHeaders httpHeaders = new WebSocketHttpHeaders();
        String JSESSIONID = cookieManager.getSessionCookieCache().getUnchecked("JSESSIONID");
        httpHeaders.add("Cookie", "JSESSIONID=" + JSESSIONID);
        return httpHeaders;
    }


    @Override
    public void handleTextMessage(@Nullable WebSocketSession session, TextMessage message) {
        System.out.println("message received:" + message.getPayload());
    }


}
