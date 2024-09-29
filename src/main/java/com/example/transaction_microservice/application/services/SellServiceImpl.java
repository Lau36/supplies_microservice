package com.example.transaction_microservice.application.services;

import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.ports.input.ISellUseCase;

public class SellServiceImpl implements SellService {
    private ISellUseCase sellUseCase;

    public SellServiceImpl(ISellUseCase sellUseCase) {
        this.sellUseCase = sellUseCase;
    }

    @Override
    public Sell addSell() {
        return sellUseCase.addSell();
    }
}
