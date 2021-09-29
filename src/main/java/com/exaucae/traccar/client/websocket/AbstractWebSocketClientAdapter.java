package com.exaucae.traccar.client.websocket;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractWebSocketClientAdapter extends AbstractWebSocketHandler {

    @Setter(onMethod_ = {@Autowired})
    private WebSocketClient webSocketClient;

    private WebSocketHandler webSocketHandler;


    protected AbstractWebSocketClientAdapter() {
    }


    protected void setWebSocketHandler(WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }

    protected abstract String getEndpoint();


    public void start() {

        URI uri = toURI(getEndpoint());

        assert uri != null : "URI built from endpoint should not be null";

        webSocketClient.doHandshake(webSocketHandler, getHeaders(), uri);
    }


    protected WebSocketHttpHeaders getHeaders() {
        return null;
    }


    private URI toURI(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            return null;
        }
    }


    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage) {
            handleTextMessage(session, (TextMessage) message);
        } else if (message instanceof BinaryMessage) {
            handleBinaryMessage(session, (BinaryMessage) message);
        } else if (message instanceof PongMessage) {
            handlePongMessage(session, (PongMessage) message);
        } else {
            throw new IllegalStateException("Unexpected WebSocket message type: " + message);
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {

        System.out.println("socket connection successfully esablished!");

    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        System.out.println("socket client connection closed: " + closeStatus.toString());
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws IOException {
        System.out.println("socket client transport error: " + exception.toString());
        System.out.println("socket client transport error message: " + exception.getMessage());
        System.out.println("socket client transport error cause: " + exception.getMessage());
        session.close();
    }


}
