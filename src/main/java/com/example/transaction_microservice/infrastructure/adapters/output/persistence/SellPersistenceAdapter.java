package com.example.transaction_microservice.infrastructure.adapters.output.persistence;

import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.domain.ports.output.ISellPersistencePort;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity.SellEntity;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper.SellMapper;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.repository.SellRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SellPersistenceAdapter implements ISellPersistencePort {

    private final SellRepository sellRepository;
    private final SellMapper sellMapper;

    @Override
    public Sell saveSell(Sell sell) {
        SellEntity sellSaved = sellRepository.save(sellMapper.toSellEntity(sell));
        return sellMapper.toSell(sellSaved);
    }
}
