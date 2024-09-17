package com.example.transaction_microservice.infrastructure.configuration.exceptionhandler;

import com.example.transaction_microservice.domain.exceptions.*;
import com.example.transaction_microservice.infrastructure.utils.Constans;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(SupplyUpdateException.class)
    public ResponseEntity<ExceptionResponse> handleSupplyUpdateException(SupplyUpdateException e) {
        return ResponseEntity.internalServerError().body(new ExceptionResponse(
                String.format(Constans.ERROR_WITH_CONEXION_MICROSERVICE, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(ConnectionErrorException.class)
    public ResponseEntity<ExceptionResponse> handleSupplyUpdateException(ConnectionErrorException e) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(new ExceptionResponse(
                String.format(Constans.ERROR_WITH_CONEXION_MICROSERVICE, e.getMessage()),
                HttpStatus.SERVICE_UNAVAILABLE.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(NotNegativeException.class)
    public ResponseEntity<ExceptionResponse> handleNotNegativeException(Exception e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constans.NOT_NEGATIVE, e.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        ));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleResponseStatusException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new ExceptionResponse(
                e.getReason(),
                e.getStatusCode().toString(),
                LocalDateTime.now()
        ));
    }

}
