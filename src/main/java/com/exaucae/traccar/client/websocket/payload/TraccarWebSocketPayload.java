package com.exaucae.traccar.client.websocket.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TraccarWebSocketPayload {

    private Position[] positions;
    private Object[] devices;
    private Object[] events;
}
