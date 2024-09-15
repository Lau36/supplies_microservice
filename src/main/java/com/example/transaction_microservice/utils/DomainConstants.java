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
    public static final String ERROR_WITH_MICROSERVICE = "Hubo un error con el microservicio de stock, revisa los datos e intentan nuevamente";
    public static final String NOT_FOUND ="No se encotró ningun item con ese id en la base de datos";
    public static final String INVALID_FIELDS= "Los datos ingresados son incorrectos, revisa nuevamente";
}
