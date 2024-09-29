package com.example.transaction_microservice.domain.usecases;

import com.example.transaction_microservice.domain.exceptions.NotInStockException;
import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.ports.input.ISellUseCase;
import com.example.transaction_microservice.domain.ports.output.*;
import com.example.transaction_microservice.domain.utils.BuyResponse;
import com.example.transaction_microservice.domain.utils.DomainConstans;
import com.example.transaction_microservice.domain.utils.Item;

import java.time.LocalDateTime;

public class SellUseCaseImpl implements ISellUseCase {

    private ISellPersistencePort sellPersistencePort;
    private CartClientPort cartClientPort;
    private StockClientPort stockClientPort;
    private ReportClientPort reportClientPort;
    private IUserPort userPort;

    public SellUseCaseImpl(ISellPersistencePort sellPersistencePort, CartClientPort cartClientPort, StockClientPort stockClientPort, ReportClientPort reportClientPort, IUserPort userPort) {
        this.sellPersistencePort = sellPersistencePort;
        this.cartClientPort = cartClientPort;
        this.stockClientPort = stockClientPort;
        this.reportClientPort = reportClientPort;
        this.userPort = userPort;
    }

    @Override
    public Sell addSell() {
        Long userId = userPort.getUserId();
        BuyResponse buyInfo = cartClientPort.buy();
        areStockValidation(buyInfo);

        Sell sell = new Sell(null,
                userId,
                buyInfo.getTotalPrice(),
                LocalDateTime.now());
        return sellPersistencePort.saveSell(sell);
    }

    private void areStockValidation(BuyResponse buyInfo) {
        for(Item item : buyInfo.getItemsInCartId()){
            if(Boolean.FALSE.equals(stockClientPort.inStock(item.getItemId(), item.getQuantity()))){
                throw new NotInStockException(String.format(DomainConstans.NOT_STOCK, item.getItemName()));
            }
        }
    }
}
