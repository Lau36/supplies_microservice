package com.example.transaction_microservice.domain.utils;

public class DomainConstans {
    private DomainConstans() {
    }

    public static final String OK_MESSAGE = "Se añadió el stock en el microservicio";
    public static final Integer ZERO = 0;
    public static final String NOT_FOUND_MESSAGE= "No se encontró el item";
    public static final String NOT_STOCK = "No hay stock del articulo %s";
    public static final String OK = "Ok";
    public static final String ERROR = "Failed";
    public static final String ITEM_OK = "Se actualizó la cantidad de stock del articulo";
    public static final String ITEM_DELETED = "Se eliminó el item del carrito, exitosamente";
    public static final String SHOP_ERROR = "Ocurrio un error con la compra";
    public static final String ROLLBACK_ERROR = "Ocurrio un error con los microservicios";
}
