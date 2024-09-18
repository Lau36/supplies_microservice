package com.example.transaction_microservice.utils;

public class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        QUANTITY
    }

    public static final String SUPPLY_CREATED = "Se creó el suministro correctamente";
    public static final String TOKEN_START_WITH = "Bearer ";
    public static final String TOKEN_KEY = "JWT_SECRET";
    public static final String ERROR_WITH_MICROSERVICE = "Hubo un error con el microservicio de stock";
    public static final String ERROR_WITH_STOCK = "Error al añadir la cantidad en stock";
}
