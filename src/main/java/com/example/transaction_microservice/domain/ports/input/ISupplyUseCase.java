package com.example.transaction_microservice.domain.ports.input;

import com.example.transaction_microservice.domain.models.Supply;

public interface ISupplyUseCase {
    Supply addSupply(Supply supply);
}
