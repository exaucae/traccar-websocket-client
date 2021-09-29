package com.exaucae.traccar.client.websocket.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Position {
    private Long id;
    private Long deviceId;
    private String protocol;
    private Timestamp deviceTime;
    private Timestamp fixTime;
    private Timestamp serverTime;
    private Boolean outdated;
    private Boolean valid;
    private int latitude;
    private int longitude;
    private int altitude;
    private int speed;
    private int course;
    private String address;
    private int accuracy;
    private Network network;
    private Attributes attributes;
}
