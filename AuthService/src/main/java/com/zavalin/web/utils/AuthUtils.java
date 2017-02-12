package com.zavalin.web.utils;

public class AuthUtils {
    public static String extractToken(String auth) {
        return auth.substring(7);
    }
}
