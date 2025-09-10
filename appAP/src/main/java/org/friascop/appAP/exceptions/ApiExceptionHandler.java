package org.friascop.appAP.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<String> handleStockInsuficienteException(StockInsuficienteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Opcional: Manejar otras RuntimeException de forma más elegante
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleGenericRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
