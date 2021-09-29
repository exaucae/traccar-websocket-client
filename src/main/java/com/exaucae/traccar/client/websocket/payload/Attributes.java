package com.exaucae.traccar.client.websocket.payload;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Attributes {
    private String attributes;
}
