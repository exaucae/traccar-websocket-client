package com.exaucae.traccar.client.websocket.util;

public class CookieParser {


    public static String getValueOf(String target, String cookies) {

        String value = null;

        if (cookies != null && target != null) {
            String[] cokkieArray = cookies.split(";");
            for (String cookie : cokkieArray) {
                if (cookie.contains(target)) {
                    value = cookie.substring(cookie.indexOf("=") + 1).trim();
                    break;
                }
            }
        }

        return value;
    }
}
