package com.example.transaction_microservice.domain;

import com.example.transaction_microservice.domain.exceptions.*;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.domain.usecases.SupplyUseCaseImpl;
import com.example.transaction_microservice.utils.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SupplyUseCaseTest {
    @Mock
    ISupplyPersistencePort supplyPersistencePort;

    @InjectMocks
    SupplyUseCaseImpl supplyUseCase;

    private Supply supply;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supply = new Supply(1L, 1L, 1L, 10, LocalDate.now());
    }

    @Test
    void addSupplySusscessfullyTest() {
        when(supplyPersistencePort.addSupply(supply)).thenReturn(supply);

        Supply supplySaved = supplyUseCase.addSupply(supply);

        assertEquals(supply, supplySaved);
        verify(supplyPersistencePort, times(1)).addSupply(supply);
    }

    @Test
    void addSupplyWhenQuantityIsNegativeTest() {
        Supply supplyNegative = new Supply(1L, 1L, 1L, -1, LocalDate.now());
        Exception exception = assertThrows(
                NotNegativeException.class,
                () -> supplyUseCase.addSupply(supplyNegative)
                );

        assertEquals(DomainConstants.Field.QUANTITY.toString(), exception.getMessage());
    }

    @Test
    void addSupplyWhenItemDoesNotExistTest() {
        when(supplyPersistencePort.addSupply(supply)).thenThrow(new ItemNotFoundInStockException(DomainConstants.NOT_FOUND));
        Exception exception = assertThrows(
                NotFoundException.class,
                () -> supplyUseCase.addSupply(supply)
        );
        assertEquals(DomainConstants.NOT_FOUND, exception.getMessage());
    }

    @Test
    void addSupplyWhenStockMicroserviceThrowsExceptionTest() {
        when(supplyPersistencePort.addSupply(supply)).thenThrow(new StockUpdateException(DomainConstants.ERROR_WITH_MICROSERVICE));
        Exception exception = assertThrows(
                SupplyUpdateException.class,
                () -> supplyUseCase.addSupply(supply)
        );
        assertEquals(DomainConstants.ERROR_WITH_MICROSERVICE, exception.getMessage());
    }

    @Test
    void addSupplyWithInvalidFieldsExceptionTest() {
        when(supplyPersistencePort.addSupply(supply)).thenThrow(new InvalidFieldsException(DomainConstants.INVALID_FIELDS));
        Exception exception = assertThrows(
                BadRequestException.class,
                () -> supplyUseCase.addSupply(supply)
        );
        assertEquals(DomainConstants.INVALID_FIELDS, exception.getMessage());
    }
}
