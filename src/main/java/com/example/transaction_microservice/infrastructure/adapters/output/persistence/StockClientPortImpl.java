package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.ports.output.StockClientPort;
import com.example.transaction_microservice.domain.utils.AddStockRequest;
import com.example.transaction_microservice.domain.utils.SubtractStockRequest;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.FeingConstans;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.StockClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockClientPortImpl implements StockClientPort {

    private final StockClient stockClient;

    @Override
    public String addStock(Supply supply) {
        AddStockRequest request = new AddStockRequest(supply.getItemId(), supply.getQuantity());
        stockClient.updateItem(request);
        return FeingConstans.OK_MESSAGE;
    }

    @Override
    public String updateStock(AddStockRequest addStockRequest) {
        stockClient.updateItem(addStockRequest);
        return FeingConstans.OK_MESSAGE;
    }

    @Override
    public Boolean inStock(Long itemId, Integer quantity) {
        return stockClient.isInStock(itemId.intValue(), quantity).getBody();
    }

    @Override
    public String substractStock(SubtractStockRequest request) {
        return stockClient.subtractStock(request).getBody();
    }

}
