package com.example.transaction_microservice.application;

import com.example.transaction_microservice.application.services.SellServiceImpl;
import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.ports.input.ISellUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SellServiceTest {
    @Mock
    private ISellUseCase sellUseCase;

    @InjectMocks
    private SellServiceImpl sellService;

    private Sell sell;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Long userId = 1L;
        sell = new Sell(1L, userId, new BigDecimal("300.0"), LocalDateTime.now());
    }

    @Test
    void testAddSell_Success() {
        when(sellUseCase.addSell()).thenReturn(sell);

        Sell result = sellService.addSell();

        verify(sellUseCase, times(1)).addSell();

        assertEquals(sell, result);
    }
}
