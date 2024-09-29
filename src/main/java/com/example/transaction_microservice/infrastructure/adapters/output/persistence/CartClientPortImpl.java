package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.ports.output.CartClientPort;
import com.example.transaction_microservice.domain.utils.BuyResponse;
import com.example.transaction_microservice.infrastructure.configuration.feignclient.CartClient;

public class CartClientPortImpl  implements CartClientPort {
    private CartClient cartClient;

    public CartClientPortImpl(CartClient cartClient) {
        this.cartClient = cartClient;
    }

    @Override
    public BuyResponse buy() {
        return cartClient.buyCart().getBody();
    }
}
