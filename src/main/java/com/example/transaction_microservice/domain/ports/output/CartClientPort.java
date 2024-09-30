package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.utils.BuyResponse;
import com.example.transaction_microservice.domain.utils.Cart;

public interface CartClientPort {
    BuyResponse buy();
    String deleteItemInCart(Long itemId);
    Cart addCart(Long itemId, Integer quantity);
}
