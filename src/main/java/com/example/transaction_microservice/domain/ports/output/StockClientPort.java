package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.utils.BuyResponse;

public interface StockClientPort {
    String addStock(Supply supply);
    Boolean inStock(Long itemId, Integer quantity);
}
