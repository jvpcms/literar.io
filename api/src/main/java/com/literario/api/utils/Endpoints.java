package com.literario.api.utils;

public class Endpoints {

    private Endpoints() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isPublic(String path) {
        return path.equals("/") || path.equals("/login") || path.startsWith("/auth");
    }

    public static boolean isProtected(String path) {
        return !isPublic(path);
    }

    public static boolean isUserSpecific(String path) {
        return path.startsWith("/users");
    }
}
