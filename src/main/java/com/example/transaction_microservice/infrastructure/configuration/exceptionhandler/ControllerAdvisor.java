package com.example.transaction_microservice.infrastructure.configuration.exceptionhandler;

import com.example.transaction_microservice.domain.exceptions.NotFoundException;
import com.example.transaction_microservice.domain.exceptions.NotNegativeException;
import com.example.transaction_microservice.domain.exceptions.SupplyUpdateException;
import com.example.transaction_microservice.infrastructure.utils.Constans;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerAdvisor {

    @ExceptionHandler(SupplyUpdateException.class)
    public ResponseEntity<ExceptionResponse> handleSupplyUpdateException(SupplyUpdateException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constans.ERROR_WITH_CONEXION_MICROSERVICE, e.getMessage()),
                HttpStatus.BAD_REQUEST.toString(),
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
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                String.format(Constans.NOT_FOUND, e.getMessage()),
                HttpStatus.NOT_FOUND.toString(),
                LocalDateTime.now()
        ));
    }

}
