package org.friascop.appAP.exceptions;

public class StockInsuficienteException extends RuntimeException  {

    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
