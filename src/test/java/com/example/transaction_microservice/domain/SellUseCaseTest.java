package com.example.transaction_microservice.domain;

import com.example.transaction_microservice.domain.exceptions.NotInStockException;
import com.example.transaction_microservice.domain.exceptions.PurchaseException;
import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.ports.output.*;
import com.example.transaction_microservice.domain.usecases.SellUseCaseImpl;
import com.example.transaction_microservice.domain.utils.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SellUseCaseTest {
    @Mock
    private ISellPersistencePort sellPersistencePort;

    @Mock
    private CartClientPort cartClientPort;

    @Mock
    private StockClientPort stockClientPort;

    @Mock
    private ReportClientPort reportClientPort;

    @Mock
    private IUserPort userPort;

    @InjectMocks
    private SellUseCaseImpl sellUseCase;

    private BuyResponse buyResponse;
    private final Long userId = 1L;
    private Sell sell;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<Item> items = Arrays.asList(
                new Item(2, "Item 1", 1L, new BigDecimal("100.00")),
                new Item(1, "Item 2", 2L, new BigDecimal("200.00"))
        );
        sell = new Sell(1L, userId, new BigDecimal("300.00"), LocalDateTime.now());

        buyResponse = new BuyResponse(items, LocalDateTime.now(), new BigDecimal("300.00"));
    }

    @Test
    void testAddSell_Success() {

        when(userPort.getUserId()).thenReturn(userId);
        when(cartClientPort.buy()).thenReturn(buyResponse);
        when(stockClientPort.inStock(anyLong(), anyInt())).thenReturn(true);
        when(stockClientPort.substractStock(any())).thenReturn(DomainConstans.ITEM_OK);
        when(cartClientPort.deleteItemInCart(anyLong())).thenReturn(DomainConstans.ITEM_DELETED);
        when(sellPersistencePort.saveSell(any(Sell.class))).thenReturn(sell);

        Sell result = sellUseCase.addSell();

        verify(stockClientPort, times(buyResponse.getItemsInCart().size())).substractStock(any());
        verify(cartClientPort, times(buyResponse.getItemsInCart().size())).deleteItemInCart(anyLong());
        verify(reportClientPort, times(1)).addShop(any(Shop.class));
        verify(sellPersistencePort, times(1)).saveSell(any(Sell.class));

        assertNotNull(result);
    }

    @Test
    void testAddSell_Fail_ReportServiceDown_Rollback() {
        when(userPort.getUserId()).thenReturn(userId);
        when(cartClientPort.buy()).thenReturn(buyResponse);
        when(stockClientPort.inStock(anyLong(), anyInt())).thenReturn(true);
        when(stockClientPort.substractStock(any())).thenReturn(DomainConstans.ITEM_OK);
        when(cartClientPort.deleteItemInCart(anyLong())).thenReturn(DomainConstans.ITEM_DELETED);
        doThrow(new PurchaseException(DomainConstans.ROLLBACK_ERROR)).when(reportClientPort).addShop(any(Shop.class));

        assertThrows(PurchaseException.class, () -> {
            sellUseCase.addSell();
        });

        verify(stockClientPort, times(buyResponse.getItemsInCart().size())).substractStock(any());
        verify(cartClientPort, times(buyResponse.getItemsInCart().size())).deleteItemInCart(anyLong());

        verify(stockClientPort, times(buyResponse.getItemsInCart().size())).updateStock(any(AddStockRequest.class));
        verify(cartClientPort, times(buyResponse.getItemsInCart().size())).addCart(anyLong(), anyInt());
    }

    @Test
    void testAddSell_StockNotAvailable_ThrowsException() {
        when(userPort.getUserId()).thenReturn(userId);
        when(cartClientPort.buy()).thenReturn(buyResponse);
        when(stockClientPort.inStock(anyLong(), anyInt())).thenReturn(false);

        assertThrows(NotInStockException.class, () -> {
            sellUseCase.addSell();
        });

        verify(stockClientPort, times(1)).inStock(anyLong(), anyInt());
        verify(stockClientPort, times(0)).substractStock(any());
        verify(cartClientPort, times(0)).deleteItemInCart(anyLong());
        verify(reportClientPort, times(0)).addShop(any(Shop.class));
        verify(sellPersistencePort, times(0)).saveSell(any(Sell.class));
    }

    @Test
    void testSubstractStockFail() {
        when(userPort.getUserId()).thenReturn(userId);
        when(cartClientPort.buy()).thenReturn(buyResponse);
        when(stockClientPort.substractStock(any())).thenReturn(DomainConstans.ERROR);
        when(stockClientPort.inStock(anyLong(), anyInt())).thenReturn(true);
        when(cartClientPort.deleteItemInCart(anyLong())).thenReturn(DomainConstans.ITEM_OK);

        assertThrows(PurchaseException.class, () -> {
            sellUseCase.addSell();
        });
        verify(stockClientPort, times(1)).substractStock(any());
        verify(sellPersistencePort, never()).saveSell(any(Sell.class));
    }

    @Test
    void testdeleteItems_Fail() {
        when(userPort.getUserId()).thenReturn(userId);
        when(cartClientPort.buy()).thenReturn(buyResponse);
        when(stockClientPort.substractStock(any())).thenReturn(DomainConstans.ITEM_OK);
        when(stockClientPort.inStock(anyLong(), anyInt())).thenReturn(true);
        when(cartClientPort.deleteItemInCart(anyLong())).thenReturn(DomainConstans.ERROR);

        assertThrows(PurchaseException.class, () -> {
            sellUseCase.addSell();
        });

        verify(stockClientPort, times(buyResponse.getItemsInCart().size())).substractStock(any());
        verify(cartClientPort, times(1)).deleteItemInCart(anyLong());
        verify(sellPersistencePort, never()).saveSell(any(Sell.class));
    }
}
