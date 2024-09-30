package com.example.transaction_microservice.domain.usecases;

import com.example.transaction_microservice.domain.exceptions.NotInStockException;
import com.example.transaction_microservice.domain.exceptions.PurchaseException;
import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.ports.input.ISellUseCase;
import com.example.transaction_microservice.domain.ports.output.*;
import com.example.transaction_microservice.domain.utils.*;

import java.time.LocalDateTime;
import java.util.List;

public class SellUseCaseImpl implements ISellUseCase {

    private final ISellPersistencePort sellPersistencePort;
    private final CartClientPort cartClientPort;
    private final StockClientPort stockClientPort;
    private final ReportClientPort reportClientPort;
    private final IUserPort userPort;

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
        validateStock(buyInfo);

        try {
            return makeSell(buyInfo, userId);
        } catch (Exception e) {
            rollbackStock(buyInfo.getItemsInCart());
            rollbackCart(buyInfo.getItemsInCart());
            throw new PurchaseException(DomainConstans.ROLLBACK_ERROR);
        }
    }

    private Sell makeSell(BuyResponse buyInfo, Long userId) {
        if(subtractStock(buyInfo.getItemsInCart()).equals(DomainConstans.OK) &&
                deleteItems(buyInfo.getItemsInCart()).equals(DomainConstans.OK))
        {

            LocalDateTime sellDate = LocalDateTime.now();
            makePurchase(buyInfo, sellDate);

            Sell sell = new Sell(null, userId, buyInfo.getTotalPrice(), sellDate);
        return sellPersistencePort.saveSell(sell);
        }
        else{
            throw new PurchaseException(DomainConstans.SHOP_ERROR);
        }
    }

    private void makePurchase(BuyResponse buyInfo, LocalDateTime sellDate){
        Shop shop = new Shop(buyInfo.getItemsInCart(), null, buyInfo.getLastUpdateCart(), sellDate, buyInfo.getTotalPrice());
        reportClientPort.addShop(shop);
    }

    private void validateStock(BuyResponse buyInfo) {
        for(Item item : buyInfo.getItemsInCart()){
            if(Boolean.FALSE.equals(stockClientPort.inStock(item.getItemId(), item.getQuantity()))){
                throw new NotInStockException(String.format(DomainConstans.NOT_STOCK, item.getName()));
            }
        }
    }

    private String subtractStock(List<Item> items){
        for(Item item : items){
            String stock = stockClientPort.substractStock(new SubtractStockRequest(Math.toIntExact(item.getItemId()), item.getQuantity()));
            if(!stock.equals(DomainConstans.ITEM_OK)){
                return DomainConstans.ERROR;
            }
        }
        return DomainConstans.OK;
    }

    private String deleteItems(List<Item> items){
        for(Item item : items){
            String elimination = cartClientPort.deleteItemInCart(item.getItemId());
            if(!elimination.equals(DomainConstans.ITEM_DELETED)){
                return DomainConstans.ERROR;
            }
        }
        return DomainConstans.OK;
    }

    private void rollbackStock(List<Item> items) {
        for (Item item : items) {
            stockClientPort.updateStock(new AddStockRequest(item.getItemId(), item.getQuantity()));
        }
    }

    private void rollbackCart(List<Item> items){
        for (Item item : items) {
            cartClientPort.addCart(item.getItemId(), item.getQuantity());
        }
    }
}
