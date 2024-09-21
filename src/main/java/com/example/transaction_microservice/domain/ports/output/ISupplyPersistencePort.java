package com.example.transaction_microservice.domain.ports.output;

import com.example.transaction_microservice.domain.models.Supply;

import java.util.Optional;

public interface ISupplyPersistencePort {
    Supply addSupply(Supply supply);
    Optional<Supply> getNextSupplyDate(Long idItem);
}
