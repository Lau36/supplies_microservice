package com.example.transaction_microservice.domain.ports.input;

import com.example.transaction_microservice.domain.models.Sell;

public interface ISellUseCase {
    Sell addSell();
}
