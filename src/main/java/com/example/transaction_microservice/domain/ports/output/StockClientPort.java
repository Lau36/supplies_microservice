package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.models.Supply;
import com.example.transaction_microservice.domain.utils.AddStockRequest;
import com.example.transaction_microservice.domain.utils.SubtractStockRequest;

public interface StockClientPort {
    String addStock(Supply supply);
    String updateStock(AddStockRequest addStockRequest);
    Boolean inStock(Long itemId, Integer quantity);
    String substractStock(SubtractStockRequest request);
}
