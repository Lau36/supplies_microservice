package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.models.Supply;

public interface ISupplyPersistencePort {
    Supply addSupply(Supply supply);
}
