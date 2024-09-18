package com.example.transaction_microservice.domain;

import com.example.transaction_microservice.domain.exceptions.*;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.output.IFeignClientPort;
import com.example.transaction_microservice.domain.ports.output.ISupplyPersistencePort;
import com.example.transaction_microservice.domain.usecases.SupplyUseCaseImpl;
import com.example.transaction_microservice.domain.utils.DomainConstans;
import com.example.transaction_microservice.utils.DomainConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SupplyUseCaseTest {
    @Mock
    ISupplyPersistencePort supplyPersistencePort;

    @Mock
    IFeignClientPort feignClientPort;

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
        when(feignClientPort.addStock(supply)).thenReturn(DomainConstans.OK_MESSAGE);
        when(supplyPersistencePort.addSupply(supply)).thenReturn(supply);

        Supply supplySaved = supplyUseCase.addSupply(supply);

        assertNotNull(supplySaved);
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
    void addSupplyWhenFeignClientFailsWithStockUpdateTest() {
        when(feignClientPort.addStock(supply)).thenReturn(DomainConstants.ERROR_WITH_STOCK);

        assertThrows(SupplyUpdateException.class, () -> supplyUseCase.addSupply(supply));
    }
}
