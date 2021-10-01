# Traccar Websocket Client

Springboot client for [traccar websocket endpoint](https://www.traccar.org/traccar-api/).

## Usage

1. Set traccar propreties to your resource path like so:

````properties
# application.properties
# traccar
traccar.name=http://localhost:82
traccar.wSname=ws://localhost:82
traccar.secret=admin
traccar.login=admin

````

2. Extend GetAllPositionWebSocketClientAdapter.java

Purpose: extend the handleMessage hook.

3. Use the main port

````java

package com.your.app.entrypoint;

import com.exaucae.traccar.client.websocket.IGetAllPositionPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TraccarWebSocketClientApplication {

    private static IGetAllPositionPort iGetAllPositionPort;


    @Autowired
    TraccarWebSocketClientApplication(IGetAllPositionPort port) {
        TraccarWebSocketClientApplication.iGetAllPositionPort = port;
    }

    public static void main(final String[] args) {

        TraccarWebSocketClientApplication.run(TraccarWebSocketClientApplication.class, args);
        WebsocketThread.start();

    }

    static class WebsocketThread implements Runnable {
        @Override
        public void run() {
            iGetAllPositionPort.handle();
        }

        static void start() {
            Thread thread = new Thread(new WebsocketThread());
            thread.start();
        }
    }
}


````

Disclaimer: This is a WIP. 80% done.

TODO:
- Ship the library to maven central

