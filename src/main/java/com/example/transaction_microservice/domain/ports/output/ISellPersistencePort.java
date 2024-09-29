package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.models.Sell;

public interface ISellPersistencePort {
    Sell saveSell(Sell sell);
}
