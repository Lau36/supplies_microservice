package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.ports.output.CartClientPort;
import com.example.transaction_microservice.domain.utils.BuyResponse;
import com.example.transaction_microservice.domain.utils.Cart;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.CartClient;

public class CartClientPortImpl  implements CartClientPort {
    private final CartClient cartClient;

    public CartClientPortImpl(CartClient cartClient) {
        this.cartClient = cartClient;
    }

    @Override
    public BuyResponse buy() {
        return cartClient.buyCart().getBody();
    }

    @Override
    public String deleteItemInCart(Long itemId) {
        return cartClient.deleteItemInCart(itemId).getBody();
    }

    @Override
    public Cart addCart(Long itemId, Integer quantity) {
        return cartClient.addCart(itemId, quantity).getBody();
    }
}
