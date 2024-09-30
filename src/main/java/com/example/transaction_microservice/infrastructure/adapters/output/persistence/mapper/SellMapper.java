package com.example.transaction_microservice.infrastructure.adapters.output.persistence.mapper;

import com.example.transaction_microservice.domain.models.Sell;
import com.example.transaction_microservice.infrastructure.adapters.output.persistence.entity.SellEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellMapper {
    Sell toSell(SellEntity entity);
    SellEntity toSellEntity(Sell sell);
}
