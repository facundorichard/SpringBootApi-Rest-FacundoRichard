package com.utn.productos_api.exception;

public class StockInsuficienteException extends RuntimeException {

    public StockInsuficienteException(Integer stock) {
        super("Stock insuficiente. Valor recibido: " + stock);
    }
}