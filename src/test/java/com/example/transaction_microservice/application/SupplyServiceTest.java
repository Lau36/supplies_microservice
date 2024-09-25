package com.example.transaction_microservice.application;

import com.example.transaction_microservice.application.services.SupplyServiceImpl;
import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.input.ISupplyUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SupplyServiceTest {
    @Mock
    ISupplyUseCase supplyUseCase;

    @InjectMocks
    SupplyServiceImpl supplyService;

    private Supply supply;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supply = new Supply(1L, 1L, 1L, 10, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void createSupply() {
    when(supplyService.addSupply(supply)).thenReturn(supply);
    Supply supplySaved = supplyService.addSupply(supply);
    assertEquals( supply, supplySaved);
    verify(supplyUseCase, times(1)).addSupply(supply);
    }

    @Test
    void getNextSupplyDateTest(){
        when(supplyService.getNextSupplyDate(supply.getItemId())).thenReturn(supply.getNextSupplyDate());
        LocalDateTime result = supplyService.getNextSupplyDate(supply.getItemId());
        assertEquals( supply.getNextSupplyDate(), result);
        verify(supplyUseCase, times(1)).getNextSupplyDate(supply.getItemId());
    }
}
