package com.example.supplies_microservice.utils;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SUPPLY_CREATED = "Se creó el suministro perfectamente";
    public static final String TOKEN_START_WITH = "Bearer ";
    public static final String TOKEN_KEY = "JWT_SECRET";
}
