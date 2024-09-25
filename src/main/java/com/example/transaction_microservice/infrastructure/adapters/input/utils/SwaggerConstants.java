package com.example.transaction_microservice.infrastructure.adapters.input.utils;

public class SwaggerConstants {
    private SwaggerConstants() {
    }

    public static final String BEARER = "bearerAuth";
    public static final String ADD_SUPPLY = "Add supply";
    public static final String ADD_SUPPLY_DESCRIPTION = "An authenticated user with the aux bodega role can add new supplies";
    public static final String ADDED_SUPPLY = "Supply created successfully";
    public static final String BAD_REQUEST = "Bad request, review the data and try again";
    public static final String NOT_FOUND_MESSAGE = "No se encontró algun item con ese id";
    public static final String OK = "Todo bien";
    public static final String GET_NEXT_SUPPLY_DATE = "Obtener la fecha del proximo suministro";
    public static final String GET_NEXT_SUPPLY_DATE_DESCRIPTION = "Se obtiene la fecha en la que se va a estar disponible ese articulo";
}
